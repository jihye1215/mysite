package com.poscoict.mysite.vo;

public class BoardVo {
	private Long no;
	private String title;
	private String contents;
	private int hit;
	private Integer Orderno;
	private Integer Groupno;
	private Integer depth;
	private String regDate;
	private Long userNo;
	private String userName;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Integer getOrderno() {
		return Orderno;
	}
	public void setOrderno(Integer orderno) {
		Orderno = orderno;
	}
	public Integer getGroupno() {
		return Groupno;
	}
	public void setGroupno(Integer groupno) {
		Groupno = groupno;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", hit=" + hit + ", Orderno="
				+ Orderno + ", Groupno=" + Groupno + ", depth=" + depth + ", regDate=" + regDate + ", userNo=" + userNo
				+ ", userName=" + userName + "]";
	}
}
