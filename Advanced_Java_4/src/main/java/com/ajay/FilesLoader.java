package com.ajay;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import com.ajay.entity.Product;
import com.ajay.dao.FilesDAO;
import com.ajay.dao.ProductDAO;

public class FilesLoader {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private FilesDAO filesDAO;
	
	@Autowired
	private CSVParser parser;
	
	@Autowired
	private FilenameFilter filter;
	
	@Autowired
	private Variables variable;
	
	@Transactional
	public void loadFiles() {
		List<String> files = filesDAO.getFiles();
		String[] newFiles = new File(variable.getCsvPath()).list(filter);
		Boolean test;
		if (!(newFiles.length == 0)) {
			for (String s : newFiles) {
				try {
					test = files.contains(s);
				} catch (NullPointerException e) {
					test = false;
				}
				if (!test) {
					try {
						filesDAO.setFiles(s);
						CSVReader reader = new CSVReaderBuilder(new FileReader(variable.getCsvPath() + "\\" + s))
								.withSkipLines(1).withCSVParser(parser).build();
						String[] nextLine;
						while ((nextLine = reader.readNext()) != null) {
							productDAO.addProduct(new Product(nextLine));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			variable.setFiles(newFiles);
		}
	}
}
