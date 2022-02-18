package com.poscoict.mysite.vo;

public class GalleryVo {
	private Long no;
	private String url;
	private String comments;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getcomments() {
		return comments;
	}
	public void setcomments(String comments) {
		this.comments = comments;
	}
	
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", url=" + url + ", comments=" + comments + "]";
	}	

}
