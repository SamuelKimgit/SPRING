package com.pcwk.ehr;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

	/**
	 * 목록 조회
	 * @param dto
	 * @return List<UserVO>
	 * @throws SQLException
	 */
	List<UserVO> doRetrieve(DTO dto) throws SQLException;
	
	/**
	 * 사용자 삭제
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doDelete(UserVO inVO) throws SQLException;
	
	/**
	 * 사용자 수정 기능
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doUpdate(UserVO inVO) throws SQLException;
	
	List<UserVO> getAll();

	int getCount(UserVO inVO) throws SQLException;

	/**
	 * 사용자 등록
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doInsert(UserVO inVO) throws SQLException;

	void deleteAll() throws SQLException;

	/**
	 * 회원단건 return
	 * @param inVO
	 * @return UserVO
	 * @throws SQLException
	 */
	UserVO doSelectOne(UserVO inVO) throws SQLException;

}