package com.hk.web.dtos;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class InfoDto implements Serializable {

	private String addr1;
	private String addr2;
	private String hpaddr;
	private String inonm;
	private String trprchaptel;
	private String trprnm;
	private String img;
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getHpaddr() {
		return hpaddr;
	}
	public void setHpaddr(String hpaddr) {
		this.hpaddr = hpaddr;
	}
	public String getInonm() {
		return inonm;
	}
	public void setInonm(String inonm) {
		this.inonm = inonm;
	}
	public String getTrprchaptel() {
		return trprchaptel;
	}
	public void setTrprchaptel(String trprchaptel) {
		this.trprchaptel = trprchaptel;
	}
	public String getTrprnm() {
		return trprnm;
	}
	public void setTrprnm(String trprnm) {
		this.trprnm = trprnm;
	}
	
	@Override
	public String toString() {
		return "InfoDto [addr1=" + addr1 + ", addr2=" + addr2 + ", hpaddr=" + hpaddr + ", inonm=" + inonm
				+ ", trprchaptel=" + trprchaptel + ", trprnm=" + trprnm + ", img=" + img + "]";
	}
	
	
	
	
	
}
