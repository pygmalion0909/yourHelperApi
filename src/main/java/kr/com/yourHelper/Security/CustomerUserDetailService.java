package kr.com.yourHelper.Security;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.com.yourHelper.Dao.MemberRepository;
import kr.com.yourHelper.QueryDto.MemberQueryDto;

@Service
public class CustomerUserDetailService implements UserDetailsService{
	private Logger logger = LoggerFactory.getLogger(CustomerUserDetailService.class);
	
	@Autowired
	MemberRepository memberRepository;
	//member Repository로 member 찾기
	
	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//member 정보
		MemberQueryDto member = memberRepository.findMemberInfoByLoginId(username);
		logger.info("member<{}>", member);
		if(member == null) {
			new UsernameNotFoundException("Username not found '"+ username + "'");
		}
		//test User객체 사용할려면 값을 넣어야 하는데 null값 넣어서는 안됨
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		//return 객체 생성
		User user = new User(member.getLoginId(), member.getPassword(), grantedAuthorities);
		
		return user;
		
	}
	
}
