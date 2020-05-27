package kr.com.yourHelper.Domain;

import java.util.List;

import kr.com.yourHelper.QueryDto.MemberQueryDto;

public class MemberList {
	
	private int count;
	private List<MemberQueryDto> list;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<MemberQueryDto> getList() {
		return list;
	}
	public void setList(List<MemberQueryDto> list) {
		this.list = list;
	}
	
	public MemberList(int count, List<MemberQueryDto> list) {
		this.count = count;
		this.list = list;
	}
	
	@Override
	public String toString() {
		return "MemberList [count=" + count + ", list=" + list + "]";
	}
	
}
