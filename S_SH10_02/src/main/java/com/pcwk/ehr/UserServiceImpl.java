package com.pcwk.ehr;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

	final Logger LOG = LogManager.getLogger(getClass());
	//상수 도입: 30, 50
	// BASICL에서 SILVER로 가는 최소 로그인 수
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	// SILVER에서 GOLD로 가는 최소 추천 수
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	
	private UserDao userDao;
	
	public UserServiceImpl() {}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int add(UserVO inVO) throws SQLException {
		
		if(null == inVO.getLevel()) {
			inVO.setLevel(Level.BASIC);
		}
		
		return this.userDao.doInsert(inVO);
	}

	/**
	 * User가 업그레이드 대상인지 확인
	 * @param user
	 * @return 대상이면(true)/대상이 아니면(false)
	 */
	private boolean canUpgradeLevel(UserVO user) {
		Level currentLevel = user.getLevel();
		switch (currentLevel) {
		case BASIC: return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
		case SILVER: return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
		case GOLD: return false;
		default: throw new IllegalAccessError("Unknown Level: "+currentLevel);
		}
	}
	
	/**
	 * 레벨 업그레이드 작업
	 * @param user
	 */
	private void upgradeLevel(UserVO user) throws SQLException {
		
		// 다음 레벨로 UP
		user.upgradeLevel();
		this.userDao.doUpdate(user);
		
	}
	
	
	@Override
	public void upgradeLevels(UserVO inVO) throws SQLException {
	/*
	 * 사용자 레벨은 : BASIC,SILVER,GOLD 
	 * 사용자가 처음 가입하면 : BASIC 
	 * 가입 이후 50회 이상 로그인 하면 : SILVER
	 * SILVET 레벨이면서 30번 이상 추천을 받으면 GOLD로 레벨 UP 
	 * 사용자 레벨의 변경 작업은 일정한 주기를 가지고 일괄처리.(트랜잭션관리)
	 */
		
		List<UserVO> list = userDao.getAll(inVO);
		
		//BASIC(50회 이상 로그인) -> SILVER 
		//SILVER(30번 이상 추천) -> GOLD
		//GOLD는 (등업)대상이 아님
		
		// 코드에 중복된 부분은 없는가 ?
		// 코드가 무엇을 하는 것인지 이해하기 불편하지 않은가 ?
		// 코드가 있어야 할 자리에 있는가 ?
		// 앞으로 변경이 일어난다면 어떤 것이 있을 수 있고, 그 변환에 쉽게 대응할 수 있게 작성되어 있는가 ?
		for (UserVO user : list) {
			
			if(canUpgradeLevel(user) == true) {
				upgradeLevel(user);
			}
		}
	}

}
