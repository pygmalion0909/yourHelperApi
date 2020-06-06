package kr.com.yourHelper.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.com.yourHelper.Security.CustomerAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired 
	private CustomerAuthenticationProvider authProvider;
	
	//consturct
	public SecurityConfig(CustomerAuthenticationProvider authProvider) {
		this.authProvider = authProvider;
	}

	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	} 
	
	/**
	 * 접근제한 및 로그인
	 * 
	 */
	@Override 
	protected void configure(HttpSecurity http) throws Exception { 
		
		//csrf해제(form방식에 풀어야하는지 확인요망!)
		http.csrf()
			.disable();
		
        http.authorizeRequests()
        	//페이지별 권한 설정
        	//.antMatchers("/admin/**").hasRole("ADMIN")
        	//.antMatchers("/user/myinfo").hasRole("MEMBER")
        	
        	//모두 접근 가능한 url설정
            .antMatchers("/login").permitAll()
            .antMatchers("/api/v1/member/create").permitAll()
            
            //모든요청에 인증된 대상만 접근하는 설정
            .anyRequest().authenticated()
            
            // 로그인 설정
            .and()
            //form로그인 방식선언
            .formLogin()
            
            //로그인 페이지 커스터아이징할때 사용
            //.loginPage("/login")
            
            //로그인 성공시 접근하는 url설정
            .defaultSuccessUrl("/api/v1/main")
            
            // 로그아웃 설정
            .and()
            .logout()
            
            //로그아웃이 성공했을 때 url이동 설정
            .logoutSuccessUrl("/api/v1/article/NT")
            
            //
            .invalidateHttpSession(true)
            
            // 403 예외처리 핸들링
            .and()
            .exceptionHandling().accessDeniedPage("/login");
        
	}
	
}
