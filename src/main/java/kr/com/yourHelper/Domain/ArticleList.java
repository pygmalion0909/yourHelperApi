package kr.com.yourHelper.Domain;

import java.util.List;

import kr.com.yourHelper.Dto.ArticleEntireDto;

public class ArticleList {
	
	private int count;
	private List<ArticleEntireDto> list;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<ArticleEntireDto> getList() {
		return list;
	}
	public void setList(List<ArticleEntireDto> list) {
		this.list = list;
	}
	
	public ArticleList(int count, List<ArticleEntireDto> list) {
		this.count = count;
		this.list = list;
	}
	
	@Override
	public String toString() {
		return "ArticleList [count=" + count + ", list=" + list + "]";
	}
	
}
