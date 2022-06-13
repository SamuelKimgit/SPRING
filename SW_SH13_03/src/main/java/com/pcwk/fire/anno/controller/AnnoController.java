package com.pcwk.fire.anno.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pcwk.fire.anno.domain.AnnoVO;
import com.pcwk.fire.anno.service.AnnoService;

@Controller
public class AnnoController {
	
	@Autowired
	private AnnoService annoService;
	
	public AnnoController() {}
	
	@RequestMapping(value = "anno/annoView.do") //요청이 들어오면 처리
	public String annoView() {
		System.out.println("===========================");
		System.out.println("=AnnoController=annoView()=");
		System.out.println("===========================");
		
		return "anno/anno";
	}
	
	@RequestMapping(value = "anno/doSelectOne.do", method = RequestMethod.POST)
	public String doSelectOne(Model model, HttpServletRequest req) throws SQLException {
		String userId = req.getParameter("userId");
		String passwd = req.getParameter("passwd");
		
		System.out.println("===========================");
		System.out.println("=userId=" +userId);
		System.out.println("=passwd=" +passwd);
		System.out.println("===========================");
		AnnoVO inVO = new AnnoVO();
		inVO.setUserId(userId);
		inVO.setPasswd(passwd);
		
		AnnoVO outVO = annoService.doSelectOne(inVO);
		model.addAttribute("vo", outVO);
		
		return "anno/anno";
	}
}
