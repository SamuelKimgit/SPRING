package com.pcwk.ehr;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // JUnit기능을 스프링 프레임으로 확장!
@ContextConfiguration(locations = "/applicationContext.xml") //applicationContext.xml loading
public class JunitUserDaoTest {
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	@Autowired //컨텍스트 프레임워크는 변수 타입과 일치하는 컨텍스트 내의 빈을 찾고, 변수에 주입
	ApplicationContext context;
	
	@Autowired
	UserDao dao;
	UserVO user01;
	UserVO user02;
	UserVO user03;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("=============");
		LOG.debug("=0.setUp()=");
		LOG.debug("=============");
		user01 = new UserVO("p05", "김무엘05", "4321");
		user02 = new UserVO("p050", "김무엘050", "4321");
		user03 = new UserVO("p0500", "김무엘0500", "4321");

		LOG.debug("context: " + context);
		LOG.debug("dao: " + dao);

		// null 사전방지 - 오류찾기 수월함
		assertNotNull(context);
		assertNotNull(dao);
	}
	
	@Test
	public void getAll() throws SQLException {
		// 1. 전체 삭제
		// 2. 데이터 3건 각각 입력
		// 3. 전체 건수 조회 = 3건
		
		// 1.전체삭제
		dao.deleteAll();
		assertEquals(0, dao.getCount(user01));
		
		// 2.데이터 3건 각각입력
		dao.add(user01);
		assertEquals(1, dao.getCount(user01));
		
		dao.add(user02);
		assertEquals(2, dao.getCount(user01));
		
		dao.add(user03);
		assertEquals(3, dao.getCount(user01));
		
		// 3.전체 건수 조회 = 3건
		List<UserVO> list = dao.getAll();
		assertEquals(3, list.size());
		
		
	}
	
	@Test
//	@Ignore
	public void addAndGet() {
		LOG.debug("=============");
		LOG.debug("=1.addAndGet()=");
		LOG.debug("=============");

		try {
			// 전체삭제
			dao.deleteAll();
			assertEquals(0, dao.getCount(user01));

			// 1건 추가
			dao.add(user01);
			assertEquals(1, dao.getCount(user01));

			// 1건 추가
			dao.add(user02);
			assertEquals(2, dao.getCount(user01));

			// 단건조회
			UserVO vsUser01 = dao.get(user01);
			isSameUser(vsUser01, user01);

			// 단건조회
			UserVO vsUser02 = dao.get(user02);
			isSameUser(vsUser02, user02);

			
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.debug("===================");
			LOG.debug("=SQLException=" + e.getMessage());
			LOG.debug("===================");
		}
	}

	private void isSameUser(UserVO vsVO, UserVO orgVO) {
		// 데이터비교
		assertEquals(vsVO.getuId(), orgVO.getuId());
		assertEquals(vsVO.getName(), orgVO.getName());
		assertEquals(vsVO.getPasswd(), orgVO.getPasswd());
	}

	@Test(expected = NullPointerException.class)
	@Ignore
	public void getFailure() throws SQLException {
		LOG.debug("=============");
		LOG.debug("=2.getFailure()=");
		LOG.debug("=============");

		dao.deleteAll();
		dao.get(user01);

	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("=============");
		LOG.debug("=9.tearDown()=");
		LOG.debug("=============");
	}

}
