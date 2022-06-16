package com.pcwk.ehr.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserVO;

//@Repository					데이터베이스 연동을 처리하는 Dao클래스																	

@Repository("userDao")
public class UserDaoImpl implements UserDao {
/*
    <!-- dao -->
    <bean id="userDao" class="com.pcwk.ehr.user.dao.UserDaoImpl"> 
      <!-- setter통한 의존관계 주입 -->
      <property name="dataSource" ref="dataSource"/>
    </bean>
 */
	final Logger LOG = LogManager.getLogger(this.getClass());

	//mybatis namespace
	final String NAVESPACE ="com.pcwk.ehr.user";
	
	//mybatis db연결객체
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	@Autowired
	private DataSource dataSource;

	//spring 제공하는 JdbcTemplate
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//RowMapper
	RowMapper<UserVO> rowMapper = new RowMapper<UserVO>() {

			@Override
			public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserVO user=new UserVO();
				user.setuId(rs.getString("u_id"));
	            user.setName(rs.getString("name"));
	            user.setPasswd( rs.getString("passwd"));
	            
	            //1 - > BASIC
	            user.setLevel(Level.valueOf(rs.getInt("u_level")));   ;
	            user.setLogin(rs.getInt("login"));
	            user.setRecommend(rs.getInt("recommend"));
	            user.setEmail(rs.getString("email"));;
	            user.setRegDt(rs.getString("reg_dt"));
	            
				return user;
			}

	};
	
	public UserDaoImpl() {}


	@Override
	public int doUpdate(UserVO inVO) throws SQLException {
		int flag = 0;
        String statement =NAVESPACE+".doUpdate";	
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");	
	
		flag = sqlSessionTemplate.update(statement, inVO);
		
		LOG.debug("flag:" + flag);
		return flag;
	}
	
	
	
	
	
	@Override
	public List<UserVO> getAll(UserVO inVO){
		List<UserVO> list= null;
	    String statement = NAVESPACE+".getAll";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");	
		
		list = sqlSessionTemplate.selectList(statement, inVO);
		
		//list = jdbcTemplate.query(sb.toString(),rowMapper,args);
		
		for(UserVO vo  :list) {
			LOG.debug("vo:"+vo.toString());
		}
		
		return list;
	}
	

	@Override
	public int getCount(UserVO inVO) throws SQLException{
		int count = 0;

		String statement = this.NAVESPACE+".getCount";
		
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");
		
		count =this.sqlSessionTemplate.selectOne(statement, inVO);
		//
		LOG.debug("==============================");
		LOG.debug("count=" + count);
		LOG.debug("==============================");			
	
		return count;
	}
	
	

	
	/**
	 * 사용자 등록
	 * 
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws ClassCastException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public int doInsert(final UserVO inVO) throws SQLException {
		int flag = 0;

		String statement = NAVESPACE+".doInsert";
		
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");	
		
		flag = this.sqlSessionTemplate.insert(statement, inVO);
		LOG.debug("flag:" + flag);
		
		return flag;
	}
	
	@Override
	public void deleteAll()throws SQLException{

		String statement = NAVESPACE+".deleteAll";
		
		LOG.debug("==============================");
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");
		
		//트랜잭션이 필요한 경우 사용(CUD)
		//jdbcTemplate.update(sb.toString());
		sqlSessionTemplate.delete(statement);
		
		
	}
	
	/**
	 * 회원단건 retruen
	 * 
	 * @param inVO
	 * @return UserVO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	@SuppressWarnings("deprecation")
	public UserVO doSelectOne(UserVO inVO) throws SQLException {
		UserVO outVO = null;
		
		String statement = this.NAVESPACE +".doSelectOne";
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
	public int doDelete(UserVO inVO) throws SQLException {
		int flag = 0;
		
		String statement = this.NAVESPACE+".doDelete";		
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" +statement);
		LOG.debug("==============================");	
				
		flag = this.sqlSessionTemplate.delete(statement, inVO);
		LOG.debug("flag:" + flag);
		return flag;
	}


	@Override
	public List<UserVO> doRetrieve(DTO dto) throws SQLException {
		String statement = NAVESPACE+".doRetrieve";
		LOG.debug("==============================");
		LOG.debug("param:" + dto.toString());
		LOG.debug("statement:" +statement);
		LOG.debug("==============================");		
		List<UserVO> list = sqlSessionTemplate.selectList(statement);
		
		for(UserVO vo  :list) {
			LOG.debug(vo);
		}
		return list;
	}


}

















