package kr.com.yourHelper.Dto;

public class ArticleDto {
	
	private String id;
	private String memberId;
	private String categoryId;
	private String title;
	private String createDate;
	private String modifyDate;
	private int hit;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	@Override
	public String toString() {
		return "ArticleDto [id=" + id + ", memberId=" + memberId + ", categoryId=" + categoryId + ", title=" + title
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", hit=" + hit + "]";
	}
	
}
