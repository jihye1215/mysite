package com.poscoict.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.security.AuthUser;
import com.poscoict.mysite.service.BoardService;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list")
	public String index(@RequestParam(value = "kwd", required = true, defaultValue = "") String keyword,
			@RequestParam(value = "pagenum", required = true, defaultValue = "1") Integer pagenum,  
			Model model) {
		Map<String, Object> map = boardService.getContentsList(pagenum, keyword);
		
		model.addAttribute("map", map);
		return "board/list";
	}
	
	@Auth
	@RequestMapping("/delete/{no}")
	public String delete(@AuthUser UserVo authUser, @PathVariable("no") Long no, Model model) {
		model.addAttribute("no",no);
		boardService.deleteContents(no);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/select/{no}")
	public String select(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no",no);
		model.addAttribute("selectvo", boardService.getContents(no));
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, BoardVo boardVo, @RequestParam(value = "p", required = true, defaultValue = "1") Integer page, @RequestParam(value = "kwd", required = true, defaultValue = "") String keyword) {
		boardVo.setUserNo(authUser.getNo());
		boardService.addContents(boardVo);
		return "redirect:/board/list";
	}

	@Auth
	@RequestMapping("/reply/{no}")
	public String reply(@AuthUser UserVo authUser, @PathVariable("no") Long no, Model model) {
		model.addAttribute("no",no);
		model.addAttribute("selectvo", boardService.getContents(no));
		return "board/write";
	}
	
	@Auth
	@RequestMapping("/update/{no}")
	public String update(@AuthUser UserVo authUser, @PathVariable("no") Long no, Model model) {
		model.addAttribute("no",no);
		model.addAttribute("selectvo", boardService.getContents(no));
		return "board/modify";
	}
	
	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, BoardVo boardVo) {
		boardService.updateContents(boardVo);
		return "redirect:/board/list";
	}

}
