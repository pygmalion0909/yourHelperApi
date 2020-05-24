package kr.com.yourHelper.Dto;

public class ArticleEntireDto {
	
	private String id;
	private String title;
	private String createDate;
	private String modifyDate;
	private int hit;
	private String content;
	private String nickName;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Override
	public String toString() {
		return "ArticleEntireDto [id=" + id + ", title=" + title + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", hit=" + hit + ", content=" + content + ", nickName=" + nickName + "]";
	}
	
}
