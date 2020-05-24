package kr.com.yourHelper.Dto;

public class ArticleCreateDto {
	
	private String title;
	private String content;
	private String nickName;
	private String fileName;
	private String fileDate;
	private String categoryCode;
	
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
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	@Override
	public String toString() {
		return "ArticleCreateDto [title=" + title + ", content=" + content + ", nickName="
				+ nickName + ", fileName=" + fileName + ", fileDate=" + fileDate + ", categoryCode=" + categoryCode
				+ "]";
	}
	
}
