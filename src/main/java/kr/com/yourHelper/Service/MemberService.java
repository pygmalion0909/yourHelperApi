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
	public MemberList getMemberInfo() {
		
		//member총개수
		int count = memberRepository.count();
		System.out.println("memberCount>>" + count);
		
		//member정보
		List<MemberDto> MemberInfoList = memberRepository.findMemberInfo();
		System.out.println("memberInfor>>" + MemberInfoList);
		
		//return
		MemberList response = new MemberList(count, MemberInfoList);
		System.out.println("memberInfoReturn" + response);
		return response;
		
	}
	
}
