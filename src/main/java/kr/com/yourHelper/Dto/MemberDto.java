package kr.com.yourHelper.Dto;

public class MemberDto {
	private String memberId;
	private String nickName;
	private String createDate;
	private String modifyDate;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
		return "MemberDto [memberId=" + memberId + ", nickName=" + nickName + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + "]";
	}
	
}
