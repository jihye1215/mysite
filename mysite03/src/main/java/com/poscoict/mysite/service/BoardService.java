package com.poscoict.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.poscoict.mysite.repository.BoardRepository;
import com.poscoict.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	// 새글 or 답글 달기
	public Boolean addContents(BoardVo vo) {
		if(vo.getOrderno() == null && vo.getGroupno() == null) {
			System.out.println("2");
			return boardRepository.insertNew(vo);
		} else {
			System.out.println("1");
			increaseGroupOrderNo(vo);
			return boardRepository.insertReply(vo);
		}
	}
	
	// 글 수정하기 전, 
	public BoardVo getContents(Long no) {
		boardRepository.views(no);
		return boardRepository.rOne(no);
	}
	
	// 글 수정
	public Boolean updateContents(BoardVo vo) {
		return boardRepository.update(vo);
	}
	
	// 글 삭제
	public Boolean deleteContents(Long no) {
		return boardRepository.delete(no);
	}
	
	// 글 리스트(찾기 결과)
	public Map<String, Object> getContentsList(int currentPage, String keyword) {
		HashMap<String, Object> map = new HashMap<>();
		List<BoardVo> blist = null; 
		if(keyword == null) {
			int cnt = boardRepository.cnt();
			int newcnt = 0;
			if(cnt%5 == 0) {
				newcnt = cnt/5;
			} else if(cnt%5 != 0) {
				newcnt = cnt/5 + 1;
			}
			map.put("newcnt", newcnt);
			map.put("cnt", cnt);
			map.put("pagenum", currentPage);
			blist = boardRepository.selectAll(currentPage);
			map.put("list", blist);
		} else {
			int cnt = boardRepository.cntKey(keyword);
			int newcnt = 0;
			if(cnt%5 == 0) {
				newcnt = cnt/5;
			} else if(cnt%5 != 0) {
				newcnt = cnt/5 + 1;
			}
			map.put("newcnt", newcnt);
			map.put("cnt", cnt);
			map.put("pagenum", currentPage);
			map.put("keyword", keyword);
			blist = boardRepository.Keyselect(keyword, currentPage);
			map.put("list", blist);
		}
		
		return map;
	}
	
	private Boolean increaseGroupOrderNo(BoardVo vo) {
		return boardRepository.updateReply(vo.getOrderno(), vo.getGroupno());
	}
	
	
}
