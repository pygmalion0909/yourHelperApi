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
	
	/**
	 * member 생성.
	 * 
	 * 필수값>> $loginId $password $nickName $autyorityCode.
	 * password는 암호화, authorityId는 authorityCode로 값을 찾음.
	 * 현재는 브라우저에서 member만 가입하게 만들어서 member의 id값인 2를 고정값으로 넣고 있음.
	 * 향후 version2에서 admin생성 페이지를 따로 만들어서 코드 작성 요망.
	 * 
	 * @param memberCreateDto $memberId $password $nickName $authorityCode.
	 * 
	 */
	public void createMember(MemberCreateDto memberCreateDto) {
		
		//비밀번호 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberCreateDto.setPassword(passwordEncoder.encode(memberCreateDto.getPassword()));
		
		//member저장
		memberCreateDto.setAuthorityId("2");
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
