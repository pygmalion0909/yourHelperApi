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
		
		//��ȣȭ ��ü ����
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		//authentication �˻�
		if(authentication == null) {
			throw new InternalAuthenticationServiceException("Authentication is null");
		}
		
		//username, password �Ҵ�
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		//username�˻�
		if(authentication.getCredentials() == null){ 
			throw new AuthenticationCredentialsNotFoundException("Credentials is null"); 
		}
		
		//DB user���� ã�� �� �˻�
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
		
		//DB user������ ��û�� user���� ����
		if(!passwordEncoder.matches(password, loadedUser.getPassword())){ 
			throw new BadCredentialsException("Password does not match stored value"); 
		}
		
		//Ȯ�ο��!
		if(!loadedUser.isCredentialsNonExpired()){ 
			throw new CredentialsExpiredException("User credentials have expired"); 
		}
		
		//������� return
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(loadedUser, null, loadedUser.getAuthorities()); 
		result.setDetails(authentication.getDetails()); 
		return result;
			
	}
	
	@Override 
	public boolean supports(Class<?> authentication) { 
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication); 
	}

}
