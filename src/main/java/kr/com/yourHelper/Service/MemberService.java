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
		
		//��й�ȣ ��ȣȭ
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberCreateDto.setPassword(passwordEncoder.encode(memberCreateDto.getPassword()));
		System.out.println("lockPass>>" + memberCreateDto);
		
		//member����
		memberRepository.save(memberCreateDto);
	}
	
	/**
	 * ��� member ����.
	 * 
	 * @return
	 * 
	 */
	public MemberList getMemberInfo() {
		
		//member�Ѱ���
		int count = memberRepository.count();
		System.out.println("memberCount>>" + count);
		
		//member����
		List<MemberDto> MemberInfoList = memberRepository.findMemberInfo();
		System.out.println("memberInfor>>" + MemberInfoList);
		
		//return
		MemberList response = new MemberList(count, MemberInfoList);
		System.out.println("memberInfoReturn" + response);
		return response;
		
	}
	
}
