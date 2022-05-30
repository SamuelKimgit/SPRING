package com.pcwk.ehr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class JTest01 {
	
	final Logger LOG = LogManager.getLogger(this.getClass());
	
/**
1. 테스트 메소드는 public 선언
2. 메소드에 @Test를 붙여준다.
*/
	@Test
	public void test() {
		LOG.debug("====================");
		LOG.debug("=test()=");
		LOG.debug("====================");
	}
	
	/**
	 * private test는 사용할 수 없다.
	 * org.junit.runners.model.InvalidTestClassError: Invalid test class 'com.pcwk.ehr.JTest01':
	   1. Method test02() should be public
	 */
	@Test
	public void test02() {
		LOG.debug("====================");
		LOG.debug("=test02()=");
		LOG.debug("====================");
	}
	
	@Test
	public void test03() {
		LOG.debug("====================");
		LOG.debug("=test03()=");
		LOG.debug("====================");
	}
}
