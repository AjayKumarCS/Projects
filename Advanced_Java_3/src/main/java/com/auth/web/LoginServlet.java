package com.auth.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auth.dao.LoginDAO;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String usrName = request.getParameter("usrName");
			String usrPass = request.getParameter("usrPass");
			LoginDAO loginDAO = new LoginDAO();
			if(loginDAO.check(usrName, usrPass)) {
				HttpSession session = request.getSession();
				session.setAttribute("userName", usrName);
				response.sendRedirect("index.jsp");
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (NullPointerException e) {
			response.sendRedirect("login.jsp");
		}
	}

}
