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
	 * ���Ͽ����� ����?
	 * �Ʒ� ��� security�� ���ϵ��� ������ ����ϸ�, ���� ������ resources/static ���͸�
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/script/**", "image/**", "/fonts/**", "lib/**");
	}
	
	/**
	 * ���ٿ����� ���Ѽ���?
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//Cross Site Request Forgery�� ����Ʈ�� Ȱ��ȭ �Ǿ� �־� ��Ȱ��ȭ ��Ŵ
		//csrf�� �����Ǿ� �ִ� ��� get����� ��������� post����� 403������ �߻� ��Ŵ
		http.csrf().disable();
		
		//api���� ���� ����
		http.authorizeRequests()
			.antMatchers("/**").permitAll();
	}
	
}
