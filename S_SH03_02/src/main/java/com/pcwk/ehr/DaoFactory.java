package com.pcwk.ehr;

public class DaoFactory {

	public UserDao userDao() {
		UserDao userDao = new UserDao(connectionMaker());
		
		return userDao;
	}
	
//	public BoardDao boardDao() {
//		ConnectionMaker connectionMaker = new NConnectionMaker();
//		UserDao boardDao = new UserDao(connectionMaker);
//		
//		return boardDao;
//	}
	
	public ConnectionMaker connectionMaker() {
		return new NConnectionMaker();
	}
}
