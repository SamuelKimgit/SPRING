package com.pcwk.ehr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
	public void doRetrieve() throws Exception {
		// SearchVO 검색구분 10 검색어 p31 의 데이터를 검색
		//searchVO.setSearchDiv("10");
		//searchVO.setSearchWord("p31");
		
		// 호출url, param, 호출방식(get/post)
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/doRetrieve.do")
				.param("pageSize" 	, String.valueOf(searchVO.getPageSize()))
				.param("pageNum" 	, String.valueOf(searchVO.getPageNum()))
				.param("searchDiv" 	, searchVO.getSearchDiv())
				.param("searchWord" , searchVO.getSearchWord());
		
		// 대역 객체 통해 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().is2xxSuccessful());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug("=======================");
		LOG.debug("result: "+result);
		LOG.debug("=======================");
		
		// jsonString to List<UserVO>
		Gson gson = new Gson();
		// gson List<UserVO> jsonString -> List<userVO>
		List<UserVO> list = gson.fromJson(result, new TypeToken<List<UserVO>>() {}.getType()); 
		
		for(UserVO vo :list) {
			LOG.debug("vo= " + vo);
		}
		
	}
	
	private void isSameUser(UserVO vsVO, UserVO orgVO) {
		assertEquals(vsVO.getuId(), orgVO.getuId());
		assertEquals(vsVO.getName(), orgVO.getName());
		assertEquals(vsVO.getPasswd(), orgVO.getPasswd());

		assertEquals(vsVO.getLevel(), orgVO.getLevel());
		assertEquals(vsVO.getLogin(), orgVO.getLogin());
		assertEquals(vsVO.getRecommend(), orgVO.getRecommend());
		assertEquals(vsVO.getEmail(), orgVO.getEmail());

	}	
	
	@Test
	@Ignore
	public void addAndGet() throws Exception {
		// 1. 기존 데이터 삭제
		// 2. 신규 데이터 등록
		// 3. 단건 데이터 조회
		// 4. 등록데이터와 비교
		
		// 1. 기존 데이터 삭제
		doDelete(user01);
		doDelete(user02);
		doDelete(user03);
		
		// 2. 신규 데이터 등록
		add(user01);
		assertEquals(1, dao.getCount(user01));
		add(user02);
		assertEquals(2, dao.getCount(user01));
		add(user03);
		assertEquals(3, dao.getCount(user01));
		
		// 3. 단건 데이터 조회
		UserVO outVO01 = doSelectOne(user01);
		UserVO outVO02 = doSelectOne(user02);
		UserVO outVO03 = doSelectOne(user03);

		// 4. 등록데이터와 비교
		isSameUser(outVO01, user01);
		isSameUser(outVO02, user02);
		isSameUser(outVO03, user03);
		
		
	}

	@Test
	@Ignore
	public void doUpdate() throws Exception {
		// 1. 기존데이터 삭제
		// 2. 단건데이터 입력
		// 3. 데이터 수정
		// 4. 데이터 비교
		
		// 1. 기존 데이터 삭제
		doDelete(user01);
		doDelete(user02);
		doDelete(user03);
		
		// 2. 신규 데이터 등록
		add(user01);
		assertEquals(1, dao.getCount(user01));

		// 2.1 단건 데이터 조회
		UserVO outVO01 = doSelectOne(user01);
		isSameUser(outVO01, user01);

		// 3. 데이터 수정
		String modifyStr = "_U";
		user01.setName(user01.getName()+modifyStr);
		user01.setPasswd(user01.getPasswd()+modifyStr);
		user01.setLevel(Level.GOLD);
		user01.setLogin(user01.getLogin()*10);
		user01.setRecommend(user01.getRecommend()+10);
		user01.setEmail(user01.getEmail()+modifyStr);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/doUpdate.do")
				.param("name", 		user01.getName())
				.param("passwd", 	user01.getPasswd())
				.param("intLevel",	user01.getIntLevel()+"")
				.param("login", 	user01.getLogin()+"")
				.param("recommend", user01.getRecommend()+"")
				.param("email", 	user01.getEmail())
				.param("uId", 		user01.getuId());
		
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
		
		//getMsgId 는 문자 이므로 문자열 "1" 로 데이터 비교해야함.
		assertEquals("1", messageVO.getMsgId());
		
		// 4. 데이터 비교
		UserVO vsVO01 = doSelectOne(user01);
		isSameUser(vsVO01, user01);
		
	}
	
//	@Test
//	@Ignore
//	public void add() throws Exception {
	public void add(UserVO user) throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/add.do")
				.param("uId", 		user.getuId())
				.param("name", 		user.getName())
				.param("passwd", 	user.getPasswd())
				.param("intLevel",	user.getIntLevel()+"")
				.param("login", 	user.getLogin()+"")
				.param("recommend", user.getRecommend()+"")
				.param("email", 	user.getEmail());
		
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
	
//	@Test
//	@Ignore
//	public void doDelete() throws Exception{
	public void doDelete(UserVO user) throws Exception{
		// GET방식으로 : http://localhost:8081/ehr/user/doDelete.do?uId=p05 와 같다.
		// 호출url, param, 호출방식(get/post)
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/doDelete.do")
				.param("uId", user.getuId());
		
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
	
//	@Test
//	@Ignore
//	public void doSelectOne() throws Exception {
	public UserVO doSelectOne(UserVO user) throws Exception {
		// 호출url, param, 호출방식(get/post)
		// GET방식으로 : http://localhost:8081/ehr/user/doSelectOne.do?uId=p05 와 같다.
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/doSelectOne.do")
				.param("uId", user.getuId());
		
		// 대역 객체 통해 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk());
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug("=======================");
		LOG.debug("result: "+result);
		LOG.debug("=======================");
		
		Gson gson = new Gson();
		//gson string to UserVO
		UserVO outVO = gson.fromJson(result, UserVO.class);
		
		return outVO;
	}

}
