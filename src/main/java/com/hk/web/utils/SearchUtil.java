package com.hk.web.utils;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class SearchUtil {
	
	public String tagTrim(Elements text,String tagName) {
		String tmpText = text.toString();
		System.out.println(tmpText.substring(tmpText.indexOf("<"+tagName+">")+tagName.length()+2));
		System.out.println(tmpText.substring(0, tmpText.indexOf("</"+tagName+">")));
		System.out.println();
		return tmpText.substring(tmpText.indexOf("<"+tagName+">")+tagName.length()+2, tmpText.indexOf("</"+tagName+">")).trim();
	}
}
