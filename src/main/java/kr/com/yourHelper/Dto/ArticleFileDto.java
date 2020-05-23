package kr.com.yourHelper.Dto;

public class ArticleFileDto {
	
	private String articleId;
	private String fileName;
	private String fileDate;
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
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
	
	@Override
	public String toString() {
		return "ArticleFileDto [articleId=" + articleId + ", fileName=" + fileName + ", fileDate=" + fileDate + "]";
	}
	
}
