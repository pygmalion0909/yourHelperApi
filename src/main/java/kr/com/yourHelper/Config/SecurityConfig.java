package kr.com.yourHelper.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * 파일에대한 권한?
	 * 아래 경로 security가 파일들은 무조건 통과하며, 파일 기준은 resources/static 디렉터리
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/script/**", "image/**", "/fonts/**", "lib/**");
	}
	
	/**
	 * 접근에대한 권한설정?
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//Cross Site Request Forgery가 디폴트로 활성화 되어 있어 비활성화 시킴
		//csrf가 설정되어 있는 경우 get방식은 상관없으나 post방식은 403에러를 발생 시킴
		http.csrf().disable();
		
		//api접근 권한 설정
		http.authorizeRequests()
			.antMatchers("/**").permitAll();
	}
	
}
