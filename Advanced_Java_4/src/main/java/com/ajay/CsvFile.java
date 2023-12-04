package com.ajay;

import java.io.File;
import java.io.FilenameFilter;

public class CsvFile implements FilenameFilter {
	
	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(".csv");
	}
}
