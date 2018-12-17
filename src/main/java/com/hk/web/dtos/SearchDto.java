package com.hk.web.dtos;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class SearchDto {
	private String img;
	private String title;
	private String subTitle;
	private String address;
	private Float score;
	
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
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
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "SearchDto [img=" + img + ", title=" + title + ", subTitle=" + subTitle + ", address=" + address
				+ ", score=" + score + "]";
	}

	
}
