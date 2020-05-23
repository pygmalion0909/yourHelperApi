package kr.com.yourHelper.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.com.yourHelper.Dao.MemberRepository;
import kr.com.yourHelper.Domain.MemberList;
import kr.com.yourHelper.Dto.MemberCreateDto;
import kr.com.yourHelper.Dto.MemberDto;

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
	
	/**
	 * 모든 member 정보.
	 * 
	 * @return
	 * 
	 */
	public MemberList allList() {
		
		//member총개수
		int count = memberRepository.count();
		
		//member정보
		List<MemberDto> allListInfo = memberRepository.findAllList();
		
		//return
		MemberList response = new MemberList(count, allListInfo);
		return response;
		
	}
	
}
