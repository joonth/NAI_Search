package com.hk.web.dtos;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class SearchDto implements Serializable {
	private String img;
	private String title;
	private String subTitle;
	private String address;
	private Float score;
	
	public String getTrprId() {
		return trprId;
	}
	public void setTrprId(String trprId) {
		this.trprId = trprId;
	}
	private String trprId;
	
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
				+ ", score=" + score + ", trprId=" + trprId + "]";
	}
	
	

	
}
