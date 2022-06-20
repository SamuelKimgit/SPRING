package com.pcwk.ehr.boot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("boot")
public class BootController {
	final Logger LOG = LogManager.getLogger(getClass());
	
	// BootController 에서 RequstMapping 을 "boot" 로 주었기때문에 bootList의 value는 "boot/bootList.do" 가 된다.
	// GET 호출시에도 ehr/boot/bootList/do로 호출
	@RequestMapping(value = "/bootList.do", method = RequestMethod.GET)
	public String bootList() {
		LOG.debug("===================");
		LOG.debug("=bootList()=");
		LOG.debug("===================");
		
		// servlet-context.xml  prefix = "/WEB-INF/views/" 와 suffix = ".jsp" 합쳐져서
		// /WEB-INF/views/boot/boot_list.jsp 가 완성된다.
		return "boot/boot_list";
	}
	
	@RequestMapping(value = "/bootReg.do", method = RequestMethod.GET)
	public String bootReg() {
		LOG.debug("===================");
		LOG.debug("=bootReg()=");
		LOG.debug("===================");
		
		// servlet-context.xml  prefix = "/WEB-INF/views/" 와 suffix = ".jsp" 합쳐져서
		// /WEB-INF/views/boot/bootReg.jsp 가 완성된다.
		return "boot/boot_reg";
	}
	
	@RequestMapping(value = "/tmpMenu.do", method = RequestMethod.GET)
	public String tmpMenu() {
		LOG.debug("===================");
		LOG.debug("=tmpMenu()=");
		LOG.debug("===================");
		
		// servlet-context.xml  prefix = "/WEB-INF/views/" 와 suffix = ".jsp" 합쳐져서
		// /WEB-INF/views/boot/bootReg.jsp 가 완성된다.
		return "menu/tmp_menu";
	}
}
