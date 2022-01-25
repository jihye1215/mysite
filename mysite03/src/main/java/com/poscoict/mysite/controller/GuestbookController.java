package com.poscoict.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.mysite.repository.GuestbookRepository;
import com.poscoict.mysite.vo.GuestbookVo;
import com.poscoict.mysite.service.GuestbookService;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("/index")
	public String index(Model model) {
		List<GuestbookVo> list = guestbookService.getMessageList();
		model.addAttribute("list", list);
		return "guestbook/index";
	}
	
	@RequestMapping("/add")
	public String add(GuestbookVo vo) {
		System.out.println("guestbookvo : " + vo);
		guestbookService.addMessage(vo);
		System.out.println("guestbookvo : " + vo);
		return "redirect:/guestbook/index";
	}
	
	@RequestMapping("/deleteform/{no}")
	public String delete(@PathVariable("no") Long no,Model model) {
		model.addAttribute("no",no);
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Long no, String password) {
		guestbookService.deleteMessage(no, password);
		return "redirect:/guestbook/index";
	}

}
