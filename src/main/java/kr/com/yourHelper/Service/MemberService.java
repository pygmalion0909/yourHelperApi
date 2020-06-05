package kr.com.yourHelper.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.com.yourHelper.Dao.MemberRepository;
import kr.com.yourHelper.Domain.MemberList;
import kr.com.yourHelper.Dto.MemberCreateDto;
import kr.com.yourHelper.Dto.MemberDto;
import kr.com.yourHelper.Dto.RolesTestDto;
import kr.com.yourHelper.QueryDto.MemberQueryDto;

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
	public void createMember(MemberCreateDto memberCreateDto) {
		
		//controller에서 받는 최초값
		logger.info("insertValueFromContoller>><{}>", memberCreateDto);
		
		//비밀번호 암호화
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		memberCreateDto.setPassword(passwordEncoder.encode(memberCreateDto.getPassword()));
//		
//		//member저장
//		memberCreateDto.setAuthorityId("2");
//		logger.debug("memberCreateDto>><{}>", memberCreateDto);
//		memberRepository.save(memberCreateDto);
		
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
	
	/**
	 * login
	 * 
	 */
	public String login(MemberDto member) {
		//1. 클라이언트에서 id,password를 넘긴다.
		//2. 클라이언트가 넘긴 id를 먼저 체크 후 없으면 throw new 던진다.
		//3. id가 있는경우 password를 체크한다.
		//4. 체크 결과가 true인경우 jwtTokenProvider에 넘긴다.
		//5. false일 경우 throw new를 던진다.
		//아래는 jwt를사용예제이다. 나중에 다시 짜기!
		//권한도 지정해서 줘야함!!!!!!!! 정보 들고 올 때 권한도 가져오기.
		logger.info("insertValueFromContoller>><{}>", member);
		
		//member정보
		MemberQueryDto memberInfo = memberRepository.findMemberInfoByLoginId(member.getLoginId());
		logger.info("memberInfo>><{}>", memberInfo);
		
		//password check
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean checkPassword = passwordEncoder.matches(member.getPassword(), memberInfo.getPassword());
		logger.info("password비교>><{}>", checkPassword);
		
		//login AND passwor true OR false 분기처리
		//loginId있는지 체크
		
		RolesTestDto rolesTestDto = new RolesTestDto();
		rolesTestDto.setId("1");
		rolesTestDto.setRole("MEMBER");
		
		List<String> roles = new ArrayList<>();
		roles.add("MEMBER");
		
		return "STring";
		//비밀번호 맞는지 체크
//		return JwtTokenProvider.createToken(member.getLoginId(), roles);
		
	}
	
}
