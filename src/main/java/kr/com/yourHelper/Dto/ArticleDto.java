package kr.com.yourHelper.Dto;

public class ArticleDto {
	
	private String articleId;
	private int memberId;
	private String title;
	private String categoryId;
	private String nickName;
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Override
	public String toString() {
		return "ArticleDto [articleId=" + articleId + ", memberId=" + memberId + ", title=" + title + ", categoryId="
				+ categoryId + ", nickName=" + nickName + "]";
	}
	
}
