package com.poscoict.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.GalleryRepository;
import com.poscoict.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	@Autowired
	private GalleryRepository galleryRepository;
	
	public Map<String, Object> getImages() {
		HashMap<String, Object> map = new HashMap<>();
		List<GalleryVo> glist = galleryRepository.getImages();
		map.put("glist", glist);
		return map;
	}
	
	public Boolean saveImage(GalleryVo galleryVo) {
		return galleryRepository.saveImage(galleryVo) == 1;
	}
	
	public Boolean removeImage(Long no) {
		return galleryRepository.removeImage(no);
	}
}
