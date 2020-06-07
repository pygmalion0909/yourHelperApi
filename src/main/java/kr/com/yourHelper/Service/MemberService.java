package kr.com.yourHelper.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.com.yourHelper.Domain.MemberList;
import kr.com.yourHelper.Dto.MemberCreateDto;
import kr.com.yourHelper.Dto.MemberDto;
import kr.com.yourHelper.Dto.RolesTestDto;
import kr.com.yourHelper.QueryDto.MemberQueryDto;
import kr.com.yourHelper.Repository.MemberRepository;

@Service
public class MemberService {
	private Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	@Autowired
	MemberRepository memberRepository;

	/**
	 * member ����.
	 * 
	 * �ʼ���>> $loginId $password $nickName $autyorityCode.
	 * <strong>
	 * password�� ��ȣȭ, authorityId�� authorityCode�� ���� ã��.
	 * ����� ���������� member�� �����ϰ� ���� member�� id���� 2�� ���������� �ְ� ����.
	 * ���� version2���� admin���� �������� ���� ���� �ڵ� �ۼ� ���.
	 * ���� �� authority�ڵ�� id�� ã�Ƽ� member���̺� ã�ƾ���.
	 * </strong>
	 * 
	 * @param memberCreateDto $memberId $password $nickName $authorityCode.
	 * 
	 */
	public String createMember(MemberCreateDto memberCreateDto) {
		
		//controller���� �޴� ���ʰ�
		logger.info("insertValueFromContoller>><{}>", memberCreateDto);
		
		//loginId �ߺ�üũ ����
		int loginIdCheck = memberRepository.checkLoginId(memberCreateDto);
		logger.info("loginIdCheck<{}>", loginIdCheck);
		
		if(loginIdCheck == 0) {
			
			//nickName �ߺ�üũ
			int nickNameCheck = memberRepository.checkNickname(memberCreateDto);
			logger.info("nickNameCheck<{}>", nickNameCheck);
			
			if(nickNameCheck == 0) {
				//��й�ȣ ��ȣȭ
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				memberCreateDto.setPassword(passwordEncoder.encode(memberCreateDto.getPassword()));
			
				//member����
				memberCreateDto.setAuthorityId("2");
				logger.debug("memberCreateDto>><{}>", memberCreateDto);
				memberRepository.save(memberCreateDto);
				return "���Լ��� �Ͽ����ϴ�.";
				
			}else {
				return "�г��� �ߺ� �Դϴ�.";
			}
			
		}else {
			return "���̵� �ߺ� �Դϴ�.";
		}
		
	}
	
	/**
	 * ��� member ����.
	 * 
	 * @return $count [$loginId $nickName $createDate $modifyDate].
	 * 
	 */
	public MemberList getMemberInfo() {
		
		//member�Ѱ���
		int count = memberRepository.count();
		logger.debug("memberCount>><{}>", count);
		
		//member����
		List<MemberQueryDto> MemberInfoList = memberRepository.findMemberInfo();
		logger.debug("memberInfor>><{}>", MemberInfoList);
		
		//return
		MemberList response = new MemberList(count, MemberInfoList);
		logger.info("returnMemberList<{}>", response);
		
		return response;
		
	}
		
}
