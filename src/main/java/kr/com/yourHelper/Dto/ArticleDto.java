package kr.com.yourHelper.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ArticleDto {
	private String id;
	private String title;
	private String content;
	private String createDate;
	private String updateDate;
	private int hits;
	private String articleListId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getArticleListId() {
		return articleListId;
	}
	public void setArticleListId(String articleListId) {
		this.articleListId = articleListId;
	}
	
	@Override
	public String toString() {
		return "ArticleDto [id=" + id + ", title=" + title + ", content=" + content + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", hits=" + hits + ", articleListId=" + articleListId + "]";
	}
	
	
	
}
