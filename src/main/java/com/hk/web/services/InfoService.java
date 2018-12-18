package com.hk.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.web.daos.InfoDao;
import com.hk.web.dtos.commentDto;

@Service
public class InfoService {

	@Autowired
	InfoDao dao;
	
	public List<commentDto> getComment(String subTitle) {
		return dao.getComment(subTitle);
	}
	
}
