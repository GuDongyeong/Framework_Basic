package web.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.dto.Dept;
import web.service.DeptService;

@Controller
public class DeptController {
	
	private Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired private DeptService deptService;
	
	@RequestMapping(value="/dept/list", method=RequestMethod.GET)
	public void deptlist( Model model ) {
//		System.out.println("/dept/list");
		
		logger.info("/dept/list [GET]");
		
		// 전체 부서 정보 조회
		List<Dept> list = deptService.getList();
		
		// MODEL 전달
		model.addAttribute("list", list);
	}

}
