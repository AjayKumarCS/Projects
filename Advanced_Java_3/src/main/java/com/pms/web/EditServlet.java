package com.pms.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.pms.dao.ProductDAO;

public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = 0;
		String title = null;
		String quantity = null;
		String size = null;
		String file_name = "";
		long file_size = 0;
		int maxFileSize = 1048576;

		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			return;
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);
		try {
			List<FileItem> fields = upload.parseRequest(request);
			Iterator<FileItem> it = fields.iterator();
			if (!it.hasNext()) {
				return;
			}

			while (it.hasNext()) {
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				if (isFormField) {
					if (title == null) {
						if (fileItem.getFieldName().equals("title")) {
							title = fileItem.getString();
						}
					}
					if (quantity == null) {
						if (fileItem.getFieldName().equals("quantity")) {
							quantity = fileItem.getString();
						}
					}
					if (size == null) {
						if (fileItem.getFieldName().equals("size")) {
							size = fileItem.getString();
						}
					}
					if (id == 0) {
						if (fileItem.getFieldName().equals("id")) {
							id = Integer.parseInt(fileItem.getString());
							System.out.println(id);
						}
					}
				} else {
					if (fileItem.getSize() > 0) {

						file_name = fileItem.getName();
						file_size = fileItem.getSize();
						File file;
						try {
							file = new File(
									"C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\uploaded_files\\"
											+ file_name);
							if (file.exists()) {
								while (file.exists()) {
									file_name = 1 + file_name;
									file = new File(
											"C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\uploaded_files\\"
													+ file_name);
								}
							}
							fileItem.write(file);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} 
				}
			}
			if(file_size == 0) {
				ProductDAO productDAO = new ProductDAO();
				productDAO.updateProduct(id, title, quantity, size);
			} else {
				ProductDAO productDAO = new ProductDAO();
				productDAO.updateProduct(id, title, quantity, size, file_name, file_size);
			}
			response.sendRedirect("index.jsp");

		} catch (Exception e) {
			response.sendRedirect("index.jsp");
		}
	}

}
