package kr.com.yourHelper.Dto;

public class ArticleContentDto {
	
	private String articleId;
	private String content;
	private String createDate;
	private String modifyDate;
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
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
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	@Override
	public String toString() {
		return "ArticleContentDto [articleId=" + articleId + ", content=" + content + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + "]";
	}
	
}
