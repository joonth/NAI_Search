package com.hk.web.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.web.daos.SearchDao;

@Service
public class SearchService {
	
	@Autowired
	SearchDao dao;
	
	public Float getScore(String subTitle) {
		List<Integer> list = new ArrayList<>();
		list = dao.getScore(subTitle);
		Float answer = 0f;
		if(list.size()==0) {
			return 0f;
		}else {
			for(int score : list) {
				answer += score;
			}
			return (float) (Math.round((answer/list.size())*100)/100.0);
		}
	}
}
