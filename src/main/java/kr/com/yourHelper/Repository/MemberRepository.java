package kr.com.yourHelper.Repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.com.yourHelper.Dto.MemberCreateDto;
import kr.com.yourHelper.QueryDto.MemberQueryDto;

@Mapper
public interface MemberRepository {
	
	public void save(MemberCreateDto memberCreateDto);
	public int count();
	public int checkLoginId(MemberCreateDto memberCreateDto);
	public int checkNickname(MemberCreateDto memberCreateDto);
	public List<MemberQueryDto> findMemberInfo();
	public String findMemberIdByNickName(String nickName);
	public MemberQueryDto findMemberInfoByMemberId(String id);
	public MemberQueryDto findMemberInfoByLoginId(String loginId);
	
}
