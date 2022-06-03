package com.pcwk.ehr;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public interface UserDao {

	List<UserVO> getAll();

	int getCount(UserVO inVO) throws SQLException;

	int add(UserVO inVO) throws SQLException;

	void deleteAll() throws SQLException;

	UserVO get(UserVO inVO) throws SQLException;

}