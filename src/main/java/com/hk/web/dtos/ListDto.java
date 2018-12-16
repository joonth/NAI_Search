package com.hk.web.dtos;

import org.jsoup.select.Elements;

public class ListDto {
	private String img;
	private String title;
	private Elements subTitle;
	private Elements address;
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Elements getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(Elements subTitle) {
		this.subTitle = subTitle;
	}
	public Elements getAddress() {
		return address;
	}
	public void setAddress(Elements address) {
		this.address = address;
	}
	
	
	
}
