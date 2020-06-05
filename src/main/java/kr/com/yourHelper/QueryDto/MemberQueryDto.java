package kr.com.yourHelper.QueryDto;

public class MemberQueryDto {
	
	private String loginId;
	private String nickName;
	private String createDate;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
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
	
	@Override
	public String toString() {
		return "MemberQueryDto [loginId=" + loginId + ", nickName=" + nickName + ", createDate=" + createDate
				+ ", password=" + password + "]";
	}
	
}
