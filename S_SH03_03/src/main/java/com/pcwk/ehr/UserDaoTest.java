package com.pcwk.ehr;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoTest {
	static final Logger LOG = LogManager.getLogger(UserDaoTest.class);
	
	UserDao dao;
	UserVO userVO;
	
	ApplicationContext context;
	
	public UserDaoTest() {
		context = new AnnotationConfigApplicationContext(DaoFactory.class);
		dao = context.getBean("userDao",UserDao.class);
		LOG.debug("=======================");
		LOG.debug("=context="+context);
		LOG.debug("=dao="+dao);
		LOG.debug("=======================");
		//dao = new DaoFactory().userDao();
		userVO = new UserVO("p05", "김무엘5", "4321");
	}
	
	public void get()throws SQLException, ClassNotFoundException {
		UserVO outVO = dao.get(userVO);
		if(null != outVO) {
			LOG.debug("=======================");
			LOG.debug("=조회성공=");
			LOG.debug("=======================");
		}else {
			LOG.debug("=======================");
			LOG.debug("=조회실패=");
			LOG.debug("=======================");
		}
	}
	
	public void add()throws SQLException, ClassNotFoundException{
		int flag = dao.add(userVO);
		if(1==flag) {
			LOG.debug("=======================");
			LOG.debug("=등록성공=");
			LOG.debug("=======================");
		}else {
			LOG.debug("=======================");
			LOG.debug("=등록실패=");
			LOG.debug("=======================");
		}
	}
	
	public static void main(String[] args) {
		UserDaoTest main = new UserDaoTest();
		
		try {
			main.add();
			main.get();
		} catch (ClassNotFoundException e) {
			LOG.debug("=======================");
			LOG.debug("ClassNotFoundException: " + e.getMessage());
			LOG.debug("=======================");
			e.printStackTrace();
		} catch (SQLException e) {
			LOG.debug("=======================");
			LOG.debug("SQLException: " + e.getMessage());
			LOG.debug("=======================");
			e.printStackTrace();
		}
	}
}
