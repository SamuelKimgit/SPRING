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

import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserVO;

@RunWith(SpringJUnit4ClassRunner.class) // JUnit기능을 스프링 프레임으로 확장!
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
}) //applicationContext.xml loading
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
		user01 = new UserVO("p05", "김무엘05", "4321", Level.BASIC, 1, 0, "rlatkandpf30@naver.com", "날짜_사용안함");
		user02 = new UserVO("p050", "김무엘050", "4321", Level.SILVER, 50, 2, "rlatkandpf30@naver.com", "날짜_사용안함");
		user03 = new UserVO("p0500", "김무엘0500", "4321", Level.GOLD, 100, 31, "rlatkandpf30@naver.com", "날짜_사용안함");

		LOG.debug("context: " + context);
		LOG.debug("dao: " + dao);

		// null 사전방지 - 오류찾기 수월함
		assertNotNull(context);
		assertNotNull(dao);
	}
	
	@Test
	@Ignore
	public void doUpdate() throws SQLException {
		LOG.debug("=============");
		LOG.debug("=3.doUpdate()=");
		LOG.debug("=============");
		// 1. 전체 삭제
		// 2. 신규 등록 : user01
		// 3. 한건 조회 : user01
		// 4. 조회된 데이터 수정 : user01
		// 5. 수정
		// 6. 한건 조회 비교
		
		// 1. 전체 삭제
//		dao.deleteAll();
		
		// 1. 단건 삭제
		dao.doDelete(user01);
		dao.doDelete(user02);
		dao.doDelete(user03);
		assertEquals(0, dao.getCount(user01));
		
		// 2. 신규 등록 : user01
		dao.doInsert(user01);
		assertEquals(1, dao.getCount(user01));
		
		// 3. 한건 조회 : user01
		UserVO vsVO = dao.doSelectOne(user01);
		isSameUser(vsVO, user01);
		
		// 4. 조회된 데이터 수정 : user01
		String upStr = "_U";
		int upInt = 10;
		
		user01.setName(user01.getName()+upStr);
		user01.setPasswd(user01.getPasswd()+upStr);
		user01.setLevel(Level.SILVER);
		user01.setLogin(user01.getLogin()+upInt);
		user01.setRecommend(user01.getRecommend()+upInt);
		user01.setEmail(user01.getEmail()+upStr);
				
		// 5. 수정
		dao.doUpdate(user01);
		
		// 6. 한건 조회 비교
		vsVO = dao.doSelectOne(user01);
		isSameUser(vsVO, user01);
	}
	
	
	@Test
	@Ignore
	public void getAll() throws SQLException {
		LOG.debug("=============");
		LOG.debug("=2.getAll()=");
		LOG.debug("=============");
		// 1. 전체 삭제
		// 2. 데이터 3건 각각 입력
		// 3. 전체 건수 조회 = 3건
		
		// 1.전체삭제
//		dao.deleteAll();
		dao.doDelete(user01);
		dao.doDelete(user02);
		dao.doDelete(user03);
		assertEquals(0, dao.getCount(user01));
		
		// 2.데이터 3건 각각입력
		dao.doInsert(user01);
		assertEquals(1, dao.getCount(user01));
		
		dao.doInsert(user02);
		assertEquals(2, dao.getCount(user01));
		
		dao.doInsert(user03);
		assertEquals(3, dao.getCount(user01));
		
		// 3.전체 건수 조회 = 3건
		List<UserVO> list = dao.getAll(user01);
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
//			dao.deleteAll();
			// 단건삭제
			dao.doDelete(user01);
			dao.doDelete(user02);
			dao.doDelete(user03);
			assertEquals(0, dao.getCount(user01));

			// 1건 추가
			dao.doInsert(user01);
			assertEquals(1, dao.getCount(user01));

			// 1건 추가
			dao.doInsert(user02);
			assertEquals(2, dao.getCount(user01));

			// 단건조회
			UserVO vsUser01 = dao.doSelectOne(user01);
			isSameUser(vsUser01, user01);

			// 단건조회
			UserVO vsUser02 = dao.doSelectOne(user02);
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
		
		assertEquals(vsVO.getLevel(), orgVO.getLevel());
		assertEquals(vsVO.getLogin(), orgVO.getLogin());
		assertEquals(vsVO.getRecommend(), orgVO.getRecommend());
		assertEquals(vsVO.getEmail(), orgVO.getEmail());
	}

	@Test(expected = NullPointerException.class)
	@Ignore
	public void getFailure() throws SQLException {
		LOG.debug("=============");
		LOG.debug("=2.getFailure()=");
		LOG.debug("=============");

		dao.deleteAll();
		dao.doSelectOne(user01);

	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("=============");
		LOG.debug("=9.tearDown()=");
		LOG.debug("=============");
	}

}
