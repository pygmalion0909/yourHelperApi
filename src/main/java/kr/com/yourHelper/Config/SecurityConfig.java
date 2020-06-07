package kr.com.yourHelper.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
            .antMatchers("/loginPage").permitAll()
            .antMatchers("/api/v1/member/create").permitAll()
            
            //모든요청에 인증된 대상만 접근하는 설정
            .anyRequest().authenticated()
            .and()
            
            //cors설정
            .cors()
            .and()
            
            // 로그인 설정
            //form로그인 방식선언
            .formLogin()
            
            //로그인 요청이 들어왔을 때 spring security가 인증하는 경로 설정
            .loginProcessingUrl("/api/v1/login")
            
            //로그인 ul 페이지 정보 제공 url경로 설정
            .loginPage("/loginPage")
            
            //로그인 성공시 접근하는 url설정
//            .defaultSuccessUrl("/api/v1/main")
            
//          	파라티터 이름 바꾸는듯   
//            .usernameParameter("id")
//            .passwordParameter("password");
            
            // 로그아웃 설정
            .and()
            //기본적으로 security에서 "/logout"로 접속하면 세션 삭제하게 기능 구현 해놈
            .logout()
            
            //로그아웃 커스터아이징할때 사용
            //.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
            
            //로그아웃이 성공했을 때 url이동 설정
            .logoutSuccessUrl("/api/v1/login")
            
            //
            .invalidateHttpSession(true)
            
            // 403 예외처리 핸들링
            .and()
            .exceptionHandling().accessDeniedPage("/login");
        
	}
	
	/*
	 * cors설정
	 * 
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("*");
		configuration.addAllowedMethod("*");
		configuration.addAllowedHeader("*");
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(3600L);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
}
