package kr.com.yourHelper.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.com.yourHelper.Dao.MemberRepository;
import kr.com.yourHelper.Dto.MemberCreateDto;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;
	
	public void createMember(MemberCreateDto memberCreateDto) {
		
		//비밀번호 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberCreateDto.setPassword(passwordEncoder.encode(memberCreateDto.getPassword()));
		System.out.println("lockPass>>" + memberCreateDto);
		
		//member저장
		memberRepository.save(memberCreateDto);
		
	}
	
}
