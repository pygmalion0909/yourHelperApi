package kr.com.yourHelper.Domain;

import java.util.List;

import kr.com.yourHelper.Dto.MemberDto;

public class MemberList {
	private int count;
	private List<MemberDto> list;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<MemberDto> getList() {
		return list;
	}
	public void setList(List<MemberDto> list) {
		this.list = list;
	}

	public MemberList(int count, List<MemberDto> list){
		this.count = count;
		this.list = list;
	}
	
	@Override
	public String toString() {
		return "MemberList [count=" + count + ", list=" + list + "]";
	}
	
}
