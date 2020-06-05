package kr.com.yourHelper.Dto;

public class MemberDto {
	
	private String loginId;
	private String password;
	
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
	
	@Override
	public String toString() {
		return "MemberDto [loginId=" + loginId + ", password=" + password + "]";
	}
	
}
