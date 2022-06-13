package com.pcwk.fire.anno.service;

import java.sql.SQLException;

import com.pcwk.fire.anno.domain.AnnoVO;

public interface AnnoService {
	public AnnoVO doSelectOne(Object inVO)throws SQLException;
	
}
