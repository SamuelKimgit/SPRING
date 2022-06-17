package com.pcwk.ehr;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // JUnit기능을 스프링 프레임으로 확장!
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"

}) // applicationContext.xml loading
public class JunitUserControllerTest {
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext; 
	
	//브라우저 대역(Mock)
	MockMvc mockMvc;
	
	@Autowired
	UserDao dao;
	UserVO user01;
	UserVO user02;
	UserVO user03;
	
	SearchVO searchVO;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("=======================");
		LOG.debug("=0.setUp()=");
		LOG.debug("=======================");
		searchVO = new SearchVO(10, 1, "", "");

		user01 = new UserVO("p05", "김무엘05", "4321", Level.BASIC, 1, 0, "rlatkandpf30@naver.com", "날짜_사용안함");
		user02 = new UserVO("p050", "김무엘050", "4321", Level.SILVER, 50, 2, "rlatkandpf30@naver.com", "날짜_사용안함");
		user03 = new UserVO("p0500", "김무엘0500", "4321", Level.GOLD, 100, 31, "rlatkandpf30@naver.com", "날짜_사용안함");

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		LOG.debug("webApplicationContext: "+webApplicationContext);
		LOG.debug("mocMvc: "+mockMvc);
		
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		
	}

	@Test
	@Ignore
	public void add() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/add.do")
				.param("uId", 		user01.getuId())
				.param("name", 		user01.getName())
				.param("passwd", 	user01.getPasswd())
				.param("intLevel",	user01.getIntLevel()+"")
				.param("login", 	user01.getLogin()+"")
				.param("recommend", user01.getRecommend()+"")
				.param("email", 	user01.getEmail());
		
		// 대역 객체 통해 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().is2xxSuccessful());
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug("=======================");
		LOG.debug("result: "+result);
		LOG.debug("=======================");
		
		// jsonString to VO
		Gson gson = new Gson();
		MessageVO messageVO = gson.fromJson(result, MessageVO.class);
		LOG.debug("=======================");
		LOG.debug("messageVO: "+messageVO);
		LOG.debug("=======================");
		
	}
	
	@Test
//	@Ignore
	public void doDelete() throws Exception{
		// 호출url, param, 호출방식(get/post)
		// GET방식으로 : http://localhost:8081/ehr/user/doDelete.do?uId=p05 와 같다.
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/doDelete.do")
				.param("uId", user01.getuId());
		
		// 대역 객체 통해 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().is2xxSuccessful());
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug("=======================");
		LOG.debug("result: "+result);
		LOG.debug("=======================");
		
		// jsonString to VO
		Gson gson = new Gson();
		MessageVO messageVO = gson.fromJson(result, MessageVO.class);
		LOG.debug("=======================");
		LOG.debug("messageVO: "+messageVO);
		LOG.debug("=======================");
	}
	
	@Test
	@Ignore
	public void doSelectOne() throws Exception {
		// 호출url, param, 호출방식(get/post)
		// GET방식으로 : http://localhost:8081/ehr/user/doSelectOne.do?uId=p05 와 같다.
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/doSelectOne.do")
				.param("uId", user01.getuId());
		
		// 대역 객체 통해 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk());
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug("=======================");
		LOG.debug("result: "+result);
		LOG.debug("=======================");
	}

}
