/**
* <pre>
* com.pcwk.ehr.board.dao
* Class Name : BoardDaoImpl.java
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
package com.pcwk.ehr.board.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.user.domain.UserVO;

@Repository("boardDao")
public class BoardDaoImpl implements BoardDao {
	
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	//mybatis namespace
	final String NAMESPACE ="com.pcwk.ehr.board";
	
	//mybatis db연결객체
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public BoardDaoImpl() {}

	@Override
	public int updateReadCnt(BoardVO inVO) throws SQLException {
		int flag = 0;
		
		String statement = NAMESPACE+".updateReadCnt";
		
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");	
		
		flag = sqlSessionTemplate.update(statement, inVO);
		LOG.debug("flag:" + flag);
		
		return flag;
	}

	@Override
	public int doDelete(BoardVO inVO) throws SQLException {
		int flag = 0;
		
		String statement = NAMESPACE+".doDelete";
		
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");	
		
		flag = sqlSessionTemplate.delete(statement, inVO);
		LOG.debug("flag:" + flag);
		
		return flag;
	}
	
	@Override
	public int doInsert(BoardVO inVO) throws SQLException {
		int flag = 0;
		
		String statement = NAMESPACE+".doInsert";
		
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");	
		
		flag = sqlSessionTemplate.insert(statement, inVO);
		LOG.debug("flag:" + flag);
		
		return flag;
	}

	@Override
	public int doUpdate(BoardVO inVO) throws SQLException {
		int flag = 0;
		
		String statement = NAMESPACE+".doUpdate";
		
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");	
		
		flag = sqlSessionTemplate.update(statement, inVO);
		LOG.debug("flag:" + flag);
		
		return flag;
	}

	@Override
	public BoardVO doSelectOne(BoardVO inVO) throws SQLException {
		BoardVO outVO = null;
		
		String statement = NAMESPACE +".doSelectOne";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");
		
		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);		
		LOG.debug("==============================");
		LOG.debug("**outVO=" + outVO.toString());
		LOG.debug("==============================");

		return outVO;
	}
	
	@Override
	public int getCount(BoardVO inVO) throws SQLException {
		int count = 0;

		String statement = NAMESPACE+".getCount";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");
		
		count =this.sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("==============================");
		LOG.debug("count=" + count);
		LOG.debug("==============================");			
	
		return count;
	}
	
	@Override
	public List<BoardVO> doRetrieve(DTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
