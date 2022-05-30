package com.pcwk.ehr;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
//import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class JunitUserDao {
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	ApplicationContext context;
	UserDao dao;
	UserVO userVO;
	UserVO userVO02;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("===========================");
		LOG.debug("=0.setUp()=");
		LOG.debug("===========================");
		userVO = new UserVO("p05", "김무엘05", "4321");
		userVO02 = new UserVO("p050", "김무엘050", "4321");
		context = new GenericXmlApplicationContext("/applicationContext.xml");
		dao = context.getBean("userDao", UserDao.class);
	
		LOG.debug("userVO: "+userVO);
		LOG.debug("context: "+context);
		LOG.debug("dao: "+dao);
		
		assertNotNull(context);
		assertNotNull(dao);
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("===========================");
		LOG.debug("=9.tearDown()=");
		LOG.debug("===========================");
	}

	@SuppressWarnings("deprecation")
	//timeout 1/1000초 단위 - 2초이내에 수행이 완료 되면 성공
	//@Ignore // test skipped
	@Test(timeout = 100000)
	public void addAndGet() {
		LOG.debug("===========================");
		LOG.debug("=2.addAndGet()=");
		LOG.debug("===========================");
		
		try {
			//전체 삭제
			dao.deleteAll();
			assertThat(dao.getCount(userVO), is(0));
			
			//1건추가
			int flag = dao.add(userVO);
			assertThat(dao.getCount(userVO), is(1));
			
			//1건추가
			dao.add(userVO02);
			assertThat(dao.getCount(userVO), is(2));
			
			//단건조회
			UserVO voVO = dao.get(userVO);
			
			//데이터비교
			assertThat(voVO.getuId(), is(userVO.getuId()));
			assertThat(voVO.getName(), is(userVO.getName()));
			assertThat(voVO.getPasswd(), is(userVO.getPasswd()));
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
