package kr.com.yourHelper.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.com.yourHelper.Dto.MemberCreateDto;
import kr.com.yourHelper.Dto.MemberDto;

@Mapper
public interface MemberRepository {
	
	public void save(MemberCreateDto memberCreateDto);
	public int count();
	public List<MemberDto> findMemberInfo();
	
}
