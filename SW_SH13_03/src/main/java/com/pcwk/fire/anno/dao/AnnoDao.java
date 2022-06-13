package com.pcwk.fire.anno.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.pcwk.fire.anno.domain.AnnoVO;

@Repository
public class AnnoDao {
	
	public AnnoDao() {}
		
	public AnnoVO doSelectOne(Object inVO) throws SQLException{
		
		System.out.println("=====================");
		System.out.println("=AnnoDao=doSelectOne=");
		System.out.println("=====================");
		AnnoVO vo = (AnnoVO) inVO;
		
		vo.setUserId(vo.getUserId()+"_pcwk");
		vo.setPasswd(vo.getPasswd()+"DDD");
		
		return vo;
	}
	
}
