package com.poscoict.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.service.FileUploadService;
import com.poscoict.mysite.service.SiteService;
import com.poscoict.mysite.vo.SiteVo;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "")
	public String main(Model model) {
		model.addAttribute("sitevo", siteService.selectAll());
		return "admin/main";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestParam(value = "file1") MultipartFile multipartFile, SiteVo siteVo) {
		String url = fileUploadService.restore(multipartFile);
		siteVo.setProfile(url);
		siteService.update(siteVo);
		if(siteService.update(siteVo)) {
			servletContext.setAttribute("sitevo", siteService.selectAll());
		}
		return "redirect:/admin";
	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}

}
