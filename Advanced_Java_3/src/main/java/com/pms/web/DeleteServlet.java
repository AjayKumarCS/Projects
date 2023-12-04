package com.pms.web;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.ProductDAO;
import com.pms.model.Product;

public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		ProductDAO productDAO = new ProductDAO();
		Product p = productDAO.selectProduct(id);
		try {
		File file = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\uploaded_files\\" + p.getImg());
		file.delete();
		} catch(Exception e) {
			e.printStackTrace();
		}
		productDAO.deleteProduct(id);
		response.sendRedirect("index.jsp");
	}

}
