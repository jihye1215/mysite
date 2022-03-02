package com.poscoict.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poscoict.mysite.dto.JsonResult;
import com.poscoict.mysite.service.GuestbookService;
import com.poscoict.mysite.vo.GuestbookVo;

@RestController("/guestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@PostMapping("/add")
	public Object create(@RequestBody GuestbookVo vo) {
		guestbookService.addMessage(vo);
		
		return JsonResult.success(vo);
	}
	
	@GetMapping("/list")
	public Object read(@RequestParam(value="sn", required = true, defaultValue = "-1") Long no) {
		List<GuestbookVo> list = guestbookService.getMessageList();
		
		return JsonResult.success(list);
	}
	
	@DeleteMapping("/delete/{no}") 
	public Object delete(
			@PathVariable("no") Long no, 
			@RequestParam(value = "password", required = true, defaultValue = "") String password) {
		System.out.println("=====================" + password);
		Boolean result = guestbookService.deleteMessage(no, password);
		Long data = 0L;
		if(result) {
			data = no;
		} else {
			data = -1L;
		}
		
		return JsonResult.success(data);
	}
}
