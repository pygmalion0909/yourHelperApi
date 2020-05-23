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
	public MemberList allList() {
		
		//member�Ѱ���
		int count = memberRepository.count();
		
		//member����
		List<MemberDto> allListInfo = memberRepository.findAllList();
		
		//return
		MemberList response = new MemberList(count, allListInfo);
		return response;
		
	}
	
}
