package com.hk.web.daos;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.web.dtos.commentDto;

@Repository
public class CommentDao {

	@Autowired
	private SqlSessionTemplate sst;
	private String ns = "com.hk.web.";
	
	public void addComment(commentDto dto) {
		sst.insert(ns+"addComment", dto);
	}
	
}
