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
	 * �������� �� �α���
	 * 
	 */
	@Override 
	protected void configure(HttpSecurity http) throws Exception { 
		
		//csrf����(form��Ŀ� Ǯ����ϴ��� Ȯ�ο��!)
		http.csrf()
			.disable();
		
        http.authorizeRequests()
        	//�������� ���� ����
        	//.antMatchers("/admin/**").hasRole("ADMIN")
        	//.antMatchers("/user/myinfo").hasRole("MEMBER")
        	
        	//��� ���� ������ url����
            .antMatchers("/loginPage").permitAll()
            .antMatchers("/api/v1/member/create").permitAll()
            
            //����û�� ������ ��� �����ϴ� ����
            .anyRequest().authenticated()
            .and()
            
            //cors����
            .cors()
            .and()
            
            // �α��� ����
            //form�α��� ��ļ���
            .formLogin()
            
            //�α��� ��û�� ������ �� spring security�� �����ϴ� ��� ����
            .loginProcessingUrl("/api/v1/login")
            
            //�α��� ul ������ ���� ���� url��� ����
            .loginPage("/loginPage")
            
            //�α��� ������ �����ϴ� url����
//            .defaultSuccessUrl("/api/v1/main")
            
//          	�Ķ�Ƽ�� �̸� �ٲٴµ�   
//            .usernameParameter("id")
//            .passwordParameter("password");
            
            // �α׾ƿ� ����
            .and()
            //�⺻������ security���� "/logout"�� �����ϸ� ���� �����ϰ� ��� ���� �س�
            .logout()
            
            //�α׾ƿ� Ŀ���;���¡�Ҷ� ���
            //.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
            
            //�α׾ƿ��� �������� �� url�̵� ����
            .logoutSuccessUrl("/api/v1/login")
            
            //
            .invalidateHttpSession(true)
            
            // 403 ����ó�� �ڵ鸵
            .and()
            .exceptionHandling().accessDeniedPage("/login");
        
	}
	
	/*
	 * cors����
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
