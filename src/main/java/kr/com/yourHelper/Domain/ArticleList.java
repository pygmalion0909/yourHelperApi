package kr.com.yourHelper.Domain;

import java.util.List;

public class ArticleList {
	
	private int count;
	private List<ArticleEntire> list;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<ArticleEntire> getList() {
		return list;
	}
	public void setList(List<ArticleEntire> list) {
		this.list = list;
	}
	
	public ArticleList(int count, List<ArticleEntire> list) {
		this.count = count;
		this.list = list;
	}
	
	@Override
	public String toString() {
		return "ArticleList [count=" + count + ", list=" + list + "]";
	}
	
}
