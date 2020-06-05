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
	
//	@Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
	
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
        	// ������ ���� ����
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/user/myinfo").hasRole("MEMBER")
            .antMatchers("/**").permitAll()
            .and() // �α��� ����
            .formLogin()
            //�α��� ������ Ŀ���;���¡�Ҷ� ���
//            .loginPage("/login")
            .defaultSuccessUrl("/api/v1/test")
            .permitAll()
            .and() // �α׾ƿ� ����
            .logout()
            .logoutSuccessUrl("/api/v1/article/NT")
            .invalidateHttpSession(true)
            .and()
            // 403 ����ó�� �ڵ鸵
            .exceptionHandling().accessDeniedPage("/user/denied");
	}
	
}
