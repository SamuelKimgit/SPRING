package com.pcwk.ehr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정 정보라는 표시
public class DaoFactory {

	@Bean //오브젝트 생성을 담당하는 IoC용 메서드
	public UserDao userDao() {
		UserDao userDao = new UserDao(connectionMaker());
		
		return userDao;
	}
	
	@Bean //오브젝트 생성을 담당하는 IoC용 메서드
	public ConnectionMaker connectionMaker() {
		return new NConnectionMaker();
	}
	
}
//	public BoardDao boardDao() {
//		ConnectionMaker connectionMaker = new NConnectionMaker();
//		UserDao boardDao = new UserDao(connectionMaker);
//		
//		return boardDao;
//	}
