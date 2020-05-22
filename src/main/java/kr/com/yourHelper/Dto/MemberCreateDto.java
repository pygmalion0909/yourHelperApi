package kr.com.yourHelper.Dto;

public class MemberCreateDto {
	private String memberId;
	private String password;
	private String nickName;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	@Override
	public String toString() {
		return "UserCreateDto [memberId=" + memberId + ", password=" + password + ", nickName=" + nickName + "]";
	}
	
}
