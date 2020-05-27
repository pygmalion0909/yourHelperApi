package kr.com.yourHelper.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.com.yourHelper.Dao.MemberRepository;
import kr.com.yourHelper.Domain.MemberList;
import kr.com.yourHelper.Dto.MemberCreateDto;
import kr.com.yourHelper.QueryDto.MemberQueryDto;

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
	public void createMember(MemberCreateDto memberCreateDto) {
		
		//controller���� �޴� ���ʰ�
		logger.info("insertValueFromContoller>><{}>", memberCreateDto);
		
		//��й�ȣ ��ȣȭ
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberCreateDto.setPassword(passwordEncoder.encode(memberCreateDto.getPassword()));
		
		//member����
		memberCreateDto.setAuthorityId("2");
		logger.debug("memberCreateDto>><{}>", memberCreateDto);
		memberRepository.save(memberCreateDto);
		
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
