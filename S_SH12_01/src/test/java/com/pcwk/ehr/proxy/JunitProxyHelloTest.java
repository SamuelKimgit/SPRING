package com.pcwk.ehr.proxy;

import static org.junit.Assert.*;

import java.lang.reflect.Proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class JunitProxyHelloTest {
	final Logger LOG = LogManager.getLogger(getClass());
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("================================");
		LOG.debug("=setUp()=");
		LOG.debug("================================");
	}

	@Test
	public void dynamicProxy() {
		//프록시 생성
		Hello proxideHello = (Hello)Proxy.newProxyInstance(
				  getClass().getClassLoader() //동적으로 생성되는 다이내믹 프록시 클래스 로딩
				, new Class[] {Hello.class} // 구현 클래스
				, new UppercaseHendler(new HelloTarget()) // 부가기능을 담은 InvocationHandler
				);
		LOG.debug(proxideHello.sayHello("pCWk"));
		LOG.debug(proxideHello.sayHi("PcwK"));
		LOG.debug(proxideHello.sayThankYou("PcWk"));
		
		
		assertEquals(proxideHello.sayHello("pCWk"), "HELLO PCWK");
		assertEquals(proxideHello.sayHi("pCWk"), "HI PCWK");
		//메서드 필터링
		assertEquals(proxideHello.sayThankYou("PcWk"), "Thank you PcWk");
	}
	
	@Test
	@Ignore
	public void proxyTest() {
		LOG.debug("================================");
		LOG.debug("=proxyTest()=");
		LOG.debug("================================");
		
		//프록시를 통해 타깃 오브젝트에 접근하도록 구성
		Hello proxiedHello = new HelloUppercase(new HelloTarget());
		
		LOG.debug(proxiedHello.sayHello("pcwk"));
		LOG.debug(proxiedHello.sayHi("pcwk"));
		LOG.debug(proxiedHello.sayThankYou("pcwk"));
		
		assertEquals(proxiedHello.sayHello("pcwk"), "HELLO PCWK");
		assertEquals(proxiedHello.sayHi("pcwk"), "HI PCWK");
		assertEquals(proxiedHello.sayThankYou("pcwk"), "THANK YOU PCWK");
	}

}
