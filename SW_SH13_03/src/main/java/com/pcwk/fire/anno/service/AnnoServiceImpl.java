package com.pcwk.fire.anno.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.fire.anno.dao.AnnoDao;
import com.pcwk.fire.anno.domain.AnnoVO;

@Service
public class AnnoServiceImpl implements AnnoService {

	@Autowired
	AnnoDao dao;
	
	@Override
	public AnnoVO doSelectOne(Object inVO) throws SQLException {
		System.out.println("===============================");
		System.out.println("=AnnoServiceImpl=doSelectOne()=");
		System.out.println("===============================");
		
		return dao.doSelectOne(inVO);
	}

}
