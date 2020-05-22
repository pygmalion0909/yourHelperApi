package kr.com.yourHelper.Dao;

import org.apache.ibatis.annotations.Mapper;

import kr.com.yourHelper.Dto.MemberCreateDto;

@Mapper
public interface MemberRepository {
	public void save(MemberCreateDto memberCreateDto);
}
