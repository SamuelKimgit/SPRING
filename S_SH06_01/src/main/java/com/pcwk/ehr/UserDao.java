package com.pcwk.ehr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UserDao{
	final Logger LOG = LogManager.getLogger(UserDao.class);
	
	private DataSource dataSource;
	
	public UserDao() {}
	
	//setter를 통한 의존성 주입
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * 데이터 COUNT
	 * @param inVO
	 * @return flag 성공(1)/실패(0)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int getCount(UserVO inVO) throws SQLException{
		int count = 0;
		//1.db연결
		//2.sql Statement(X.안씀), PreparedStatement
		//3.PreparedStatement 수행
		//4.ResultSet으로 정보를 받아와 처리
		//5.자원반납
		
		//1.db연결
		 Connection connection = dataSource.getConnection();
		
		//2.sql Statement(X.안씀), PreparedStatement
		StringBuilder sb = new StringBuilder(100);
		sb.append(" SELECT COUNT(*) AS cnt  \n");
		sb.append(" FROM hr_member          \n");
		sb.append(" WHERE u_id LIKE ?||'%'  \n");
		
		LOG.debug("=========================");
		LOG.debug("param: "+inVO.toString());
		LOG.debug("SQL:\n"+sb.toString());
		LOG.debug("=========================");
		
		//3.PreparedStatement 수행
		PreparedStatement pstmt = connection.prepareStatement(sb.toString());
		pstmt.setString(1, inVO.getuId());
		
		//4.ResultSet으로 정보를 받아와 처리
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			count = rs.getInt("cnt");
		}
		LOG.debug("=count="+count);
		
		//5.자원반납 - 생성된 순서의 역순으로 close
		rs.close();
		pstmt.close();
		connection.close();
		
		return count;
	}
	
	/**
	 * 전체 삭제
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void deleteAll()throws SQLException {
		int flag = 0;
		//1.db연결
		//2.sql Statement(X.안씀), PreparedStatement
		//3.PreparedStatement 수행
		//4.ResultSet으로 정보를 받아와 처리
		//5.자원반납
		
		//1.db연결
		Connection connection = null;
		connection = dataSource.getConnection();
		
		//2.sql
		StringBuilder sb = new StringBuilder(100);
		sb.append(" DELETE FROM hr_member \n");
		LOG.debug("================================");
		LOG.debug("SQL:\n"+sb.toString());
		LOG.debug("================================");
		
		//3.PreparedStatement 수행
		PreparedStatement pstmt = connection.prepareStatement(sb.toString());
		
		//4.ResultSet으로 정보를 받아와 처리
		flag = pstmt.executeUpdate();
		LOG.debug("=flag="+flag);
		
		//5.자원반납 - 생성된 순서의 역순으로 close
		pstmt.close();
		connection.close();
	}
	
	/**
	 * 회원 단건 return
	 * @param inVO
	 * @return outVO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public UserVO get(UserVO inVO)throws SQLException {
		UserVO outVO = null;
		//1.db연결
		//2.sql Statement(X.안씀), PreparedStatement
		//3.PreparedStatement 수행
		//4.ResultSet으로 정보를 받아와 처리
		//5.자원반납
		
		//1.db연결
		Connection connection = null;
		connection = dataSource.getConnection();
		
		//2.sql Statement(X.안씀), PreparedStatement
		StringBuilder sb = new StringBuilder(100);
		sb.append(" SELECT         \n");
		sb.append("     u_id,      \n");
		sb.append("     name,      \n");
		sb.append("     passwd     \n");
		sb.append(" FROM hr_member \n");
		sb.append(" WHERE u_id = ? \n");
		LOG.debug("=========================");
		LOG.debug("param: "+inVO.toString());
		LOG.debug("SQL:\n"+sb.toString());
		LOG.debug("=========================");
		
		//3.PreparedStatement 수행
		PreparedStatement pstmt = connection.prepareStatement(sb.toString());
		pstmt.setString(1, inVO.getuId());
		
		//4.ResultSet으로 정보를 받아와 처리
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			outVO = new UserVO();
			
			outVO.setuId(rs.getString("u_id"));
			outVO.setName(rs.getString("name"));
			outVO.setPasswd(rs.getString("passwd"));
			
		}
		
		//데이터가 없으면 예외 발생
		if(null == outVO) {
			throw new NullPointerException();
		}
		
		LOG.debug("=outVO="+outVO.toString());
		
		//5.자원반납 - 객채가 생성된 역순으로 close
		rs.close();
		pstmt.close();
		connection.close();
		
		return outVO;
	}

	/**
	 * 사용자 등록
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws ClassCastException
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public int add(UserVO inVO)throws SQLException{
		int flag = 0;
		//1.db연결
		//2.sql Statement(X.안씀), PreparedStatement
		//3.PreparedStatement 수행
		//4.ResultSet으로 정보를 받아와 처리
		//5.자원반납
		
		//1.db연결
		Connection connection = null;
		connection = dataSource.getConnection();
		
		//2.sql Statement(X.안씀), PreparedStatement
		StringBuilder sb = new StringBuilder(100);
		sb.append(" INSERT INTO hr_member (  \n");
		sb.append("     u_id,                \n");
		sb.append("     name,                \n");
		sb.append("     passwd               \n");
		sb.append(" ) VALUES (               \n");
		sb.append("     ?,                   \n");
		sb.append("     ?,                   \n");
		sb.append("     ?                    \n");
		sb.append(" )                        \n");
		LOG.debug("================================");
		LOG.debug("param: "+inVO.toString());
		LOG.debug("SQL:\n"+sb.toString());
		LOG.debug("================================");
		
		//3.PreparedStatement 수행
		PreparedStatement pstmt = connection.prepareStatement(sb.toString());
		pstmt.setString(1, inVO.getuId());
		pstmt.setString(2, inVO.getName());
		pstmt.setString(3, inVO.getPasswd());
		
		//4.ResultSet으로 정보를 받아와 처리
		flag = pstmt.executeUpdate();
		LOG.debug("=flag="+flag);
		
		//5.자원반납 - 생성된 순서의 역순으로 close
		pstmt.close();
		connection.close();
		
		return flag;
	}
	
}