package com.poscoict.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping({"", "/main"})
	public String index() {
		// return "/WEB-INF/views/main/index.jsp";
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/msg01")
	public String message01() {
		return "안녕";
	}
	
	@ResponseBody
	@RequestMapping("/msg03")
	public Object message03() {
		Map<String, Object> map = new HashMap<>();
		map.put("messae", "Hello World");
		
		return map;
	}
}
