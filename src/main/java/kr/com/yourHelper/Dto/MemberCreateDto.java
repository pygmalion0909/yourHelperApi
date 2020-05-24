package kr.com.yourHelper.Dto;

public class MemberCreateDto {
	
	private String loginId;
	private String password;
	private String nickName;
	private String authorityCode;
	private String authorityId;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAuthorityCode() {
		return authorityCode;
	}
	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}
	public String getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}
	
	@Override
	public String toString() {
		return "MemberCreateDto [loginId=" + loginId + ", password=" + password + ", nickName=" + nickName
				+ ", authorityCode=" + authorityCode + ", authorityId=" + authorityId + "]";
	}
	
}
