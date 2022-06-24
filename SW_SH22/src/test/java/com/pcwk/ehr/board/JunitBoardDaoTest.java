/**
* <pre>
* com.pcwk.ehr.board
* Class Name : JunitBoardDaoTest.java
* Description:
* Author: ITSC
* Since: 2022/06/24
* Version 0.1
* Copyright (c) by H.R.KIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2022/06/24 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.pcwk.ehr.board;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcwk.ehr.board.dao.BoardDao;
import com.pcwk.ehr.board.domain.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class) //JUnit기능을 스프링 프레임으로 확장!
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		                           "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
}) //applicationContext.xml loading
public class JunitBoardDaoTest {

	final Logger LOG = LogManager.getLogger(this.getClass());
	
	@Autowired  //컨텍스트 프레임워크는 변수 타입과 일치하는 컨텍스트 내의 빈을 찾고, 변수에 주입
	  ApplicationContext context;
	      
	@Autowired
	BoardDao dao;
	
	BoardVO board01;
	BoardVO board02;
	BoardVO board03;
	
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("====================");
		LOG.debug("=0.setUp()=");
		LOG.debug("====================");
		
		board01 = new BoardVO(5, "제목_05", "내용_05", 0, "10", "날짜_사용안함", "김무엘", "날짜_사용안함", "김무엘");
		board02 = new BoardVO(50, "제목_050", "내용_050", 0, "10", "날짜_사용안함", "김무엘", "날짜_사용안함", "김무엘");
		board03 = new BoardVO(500, "제목_0500", "내용_0500", 0, "10", "날짜_사용안함", "김무엘", "날짜_사용안함", "김무엘");
		
		LOG.debug("context:"+context);
		LOG.debug("dao:"+dao);
		
		assertNotNull(context);
		assertNotNull(dao);
	}

	@Test
	public void doUpdate() throws SQLException {
		LOG.debug("====================");
		LOG.debug("=2.doUpdate()=");
		LOG.debug("====================");
		
			// 3건 삭제
			dao.doDelete(board01);
			dao.doDelete(board02);
			dao.doDelete(board03);
			assertEquals(0, dao.getCount(board01));
			
			// 1건입력
			dao.doInsert(board01);
			assertEquals(1, dao.getCount(board01));
			
			// 입력데이터 조회 & 비교
			BoardVO vsVO = dao.doSelectOne(board01);
			isSameData(vsVO, board01);
			
			// 업데이트 변수
			String upStr = "_U";
			
			// board01 데이터 수정 
			vsVO.setTitle(vsVO.getTitle()+upStr);
			vsVO.setContents(vsVO.getContents()+upStr);
			vsVO.setDiv(vsVO.getDiv());
			vsVO.setModId(vsVO.getModId()+upStr);
			
			// update쿼리 진행
			dao.doUpdate(vsVO);
			
			// 수정된 데이터 조회 & 비교
			BoardVO resultVO = dao.doSelectOne(vsVO);
			isSameData(resultVO, vsVO);
	}
	
	@Test
	@Ignore
	public void addAndGet() throws SQLException {
		LOG.debug("====================");
		LOG.debug("=1.addAndGet()=");
		LOG.debug("====================");
		
			// 삭제
			dao.doDelete(board01);
			dao.doDelete(board02);
			dao.doDelete(board03);
			assertEquals(0, dao.getCount(board01));
			
			// 등록 1
			dao.doInsert(board01);
			assertEquals(1, dao.getCount(board01));
			// 단건 조회 & 조회 데이터 비교 1
			BoardVO outVO01 = dao.doSelectOne(board01);
			isSameData(outVO01, board01);
			
			// 등록 2
			dao.doInsert(board02);
			assertEquals(2, dao.getCount(board01));
			// 단건 조회 & 조회 데이터 비교 2
			BoardVO outVO02 = dao.doSelectOne(board02);
			isSameData(outVO02, board02);
			
			// 등록 3
			dao.doInsert(board03);
			assertEquals(3, dao.getCount(board01));
			// 단건 조회 & 조회 데이터 비교 3
			BoardVO outVO03 = dao.doSelectOne(board03);
			isSameData(outVO03, board03);
	}
	
	// 조회 데이터 비교
	private void isSameData(BoardVO vsVO, BoardVO orgVO) {
		assertEquals(vsVO.getSeq(), orgVO.getSeq());
		assertEquals(vsVO.getTitle(), orgVO.getTitle());
		assertEquals(vsVO.getContents(), orgVO.getContents());
		assertEquals(vsVO.getReadCnt(), orgVO.getReadCnt());
		assertEquals(vsVO.getDiv(), orgVO.getDiv());
		assertEquals(vsVO.getRegId(), orgVO.getRegId());
		assertEquals(vsVO.getModId(), orgVO.getModId());
	}

}
