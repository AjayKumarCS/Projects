package com.ajay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajay.dao.ProductDAO;
import com.ajay.FilesLoader;

@Service
public class SearchService {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private FilesLoader fl;
	
	@Transactional
	public List<List<String>> search(String color, String size, String gender, String sortBy){
		
		List<List<String>> s = productDAO.search(color, size, gender, sortBy);
		return s;
	}
	
	public void loadFiles() {
		fl.loadFiles();
	}
	
	@Transactional
	public List<String> getColors() {
		return productDAO.availableColors();
	}
	
}
