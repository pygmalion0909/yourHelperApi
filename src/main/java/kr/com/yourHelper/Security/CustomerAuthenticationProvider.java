package kr.com.yourHelper.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomerAuthenticationProvider implements AuthenticationProvider{
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
	@Autowired
	private CustomerUserDetailService customerUserDetailService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		//암호화 객체 생성
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		//authentication 검사
		if(authentication == null) {
			throw new InternalAuthenticationServiceException("Authentication is null");
		}
		
		//username, password 할당
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		//username검사
		if(authentication.getCredentials() == null){ 
			throw new AuthenticationCredentialsNotFoundException("Credentials is null"); 
		}
		
		//DB user정보 찾기 및 검사
		UserDetails loadedUser = customerUserDetailService.loadUserByUsername(username); 
		if(loadedUser == null){ 
			throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation"); 
		}
		
		if(!loadedUser.isAccountNonLocked()){ 
			throw new LockedException("User account is locked"); 
		}
		
		if(!loadedUser.isEnabled()){ 
			throw new DisabledException("User is disabled"); 
		} 
		
		if(!loadedUser.isAccountNonExpired()){ 
			throw new AccountExpiredException("User account has expired"); 
		}
		
		//DB user정보와 요청한 user정보 대조
		if(!passwordEncoder.matches(password, loadedUser.getPassword())){ 
			throw new BadCredentialsException("Password does not match stored value"); 
		}
		
		//확인요망!
		if(!loadedUser.isCredentialsNonExpired()){ 
			throw new CredentialsExpiredException("User credentials have expired"); 
		}
		
		//인증결과 return
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(loadedUser, null, loadedUser.getAuthorities()); 
		result.setDetails(authentication.getDetails()); 
		return result;
			
	}
	
	@Override 
	public boolean supports(Class<?> authentication) { 
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication); 
	}

}
