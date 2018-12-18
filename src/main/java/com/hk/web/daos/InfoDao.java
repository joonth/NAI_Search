package com.hk.web.daos;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.web.dtos.commentDto;

@Repository
public class InfoDao {
	
	@Autowired
	private SqlSessionTemplate sst;
	private String ns = "com.hk.web.";
	
	public List<commentDto> getComment(String subTitle) {
		List<commentDto> list = new ArrayList<>();
		list = sst.selectList(ns+"getComment", subTitle);
		return list;
	}
}
