package com.pcwk.ehr;

import static org.junit.Assert.*;

import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcwk.ehr.collection.CollectionProp;
@RunWith(SpringJUnit4ClassRunner.class)//JUnit기능을 스프링 프레임으로 확장!
@ContextConfiguration(locations = "/applicationCollectionPropBean.xml")
public class JCollectionPropBeanTest {
    final Logger LOG = LogManager.getLogger(getClass());
    
    @Autowired //컨텍스트 프레임워크는 변수 타입과 일치하는 컨텍스트 내의 빈을 찾고, 변수에 주입
    ApplicationContext context;
    
    @Autowired
    CollectionProp collectionProp;
    
	@Before
	public void setUp() throws Exception {
		LOG.debug("--------------------------");
		LOG.debug("-0 setUp()-");
		LOG.debug("--------------------------");
		
		LOG.debug("-context-"+context);
		LOG.debug("-collectionProp-"+collectionProp);
		assertNotNull(context);  
		assertNotNull(collectionProp);  
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("--------------------------");
		LOG.debug("-9 tearDown()-");
		LOG.debug("--------------------------");		
	}

	@Test
	public void test() {
		LOG.debug("===========================");
		LOG.debug("=test()=");
		LOG.debug("===========================");
		
		Properties prop = collectionProp.getAddressList();
		
		Set<Object> keys = prop.keySet();
		
		for(Object obj : keys) {
			LOG.debug("key: "+obj.toString()+", "+prop.getProperty(obj.toString()));
		}
	}

}