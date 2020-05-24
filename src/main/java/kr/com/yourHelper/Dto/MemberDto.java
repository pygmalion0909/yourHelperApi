package kr.com.yourHelper.Dto;

public class MemberDto {
	
	private String id;
	private String loginId;
	private String nickName;
	private String createDate;
	private String modifyDate;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
		return "MemberDto [loginId=" + loginId + ", nickName=" + nickName + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + "]";
	}
	
}
