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
	 * member 생성.
	 * 
	 * 필수값>> $loginId $password $nickName $autyorityCode.
	 * <strong>
	 * password는 암호화, authorityId는 authorityCode로 값을 찾음.
	 * 현재는 브라우저에서 member만 가입하게 만들어서 member의 id값인 2를 고정값으로 넣고 있음.
	 * 향후 version2에서 admin생성 페이지를 따로 만들어서 코드 작성 요망.
	 * 저장 시 authority코드로 id를 찾아서 member테이블에 찾아야함.
	 * </strong>
	 * 
	 * @param memberCreateDto $memberId $password $nickName $authorityCode.
	 * 
	 */
	public String createMember(MemberCreateDto memberCreateDto) {
		
		//controller에서 받는 최초값
		logger.info("insertValueFromContoller>><{}>", memberCreateDto);
		
		//loginId 중복체크 숫자
		int loginIdCheck = memberRepository.checkLoginId(memberCreateDto);
		logger.info("loginIdCheck<{}>", loginIdCheck);
		
		if(loginIdCheck == 0) {
			
			//nickName 중복체크
			int nickNameCheck = memberRepository.checkNickname(memberCreateDto);
			logger.info("nickNameCheck<{}>", nickNameCheck);
			
			if(nickNameCheck == 0) {
				//비밀번호 암호화
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				memberCreateDto.setPassword(passwordEncoder.encode(memberCreateDto.getPassword()));
			
				//member저장
				memberCreateDto.setAuthorityId("2");
				logger.debug("memberCreateDto>><{}>", memberCreateDto);
				memberRepository.save(memberCreateDto);
				return "가입성공 하였습니다.";
				
			}else {
				return "닉네임 중복 입니다.";
			}
			
		}else {
			return "아이디 중복 입니다.";
		}
		
	}
	
	/**
	 * 모든 member 정보.
	 * 
	 * @return $count [$loginId $nickName $createDate $modifyDate].
	 * 
	 */
	public MemberList getMemberInfo() {
		
		//member총개수
		int count = memberRepository.count();
		logger.debug("memberCount>><{}>", count);
		
		//member정보
		List<MemberQueryDto> MemberInfoList = memberRepository.findMemberInfo();
		logger.debug("memberInfor>><{}>", MemberInfoList);
		
		//return
		MemberList response = new MemberList(count, MemberInfoList);
		logger.info("returnMemberList<{}>", response);
		
		return response;
		
	}
		
}
