package kr.com.yourHelper.Dto;

public class ArticleCreateDto {
	
	private String memberId;
	private String title;
	private String content;
	private String nickName;
	private String fileName;
	private String fileDate;
	private String categoryId;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileDate() {
		return fileDate;
	}
	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	@Override
	public String toString() {
		return "ArticleCreateDto [memberId=" + memberId + ", title=" + title + ", content=" + content + ", nickName="
				+ nickName + ", fileName=" + fileName + ", fileDate=" + fileDate + ", categoryId=" + categoryId + "]";
	}
	
}
