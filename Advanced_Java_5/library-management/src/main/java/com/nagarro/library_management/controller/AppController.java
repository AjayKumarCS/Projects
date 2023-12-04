package com.nagarro.library_management.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.library_management.service.LibraryManagementService;
import com.nagarro.library_management.service.LoginService;

@Controller
public class AppController {

	@Autowired
	private LoginService login;
	
	@Autowired
	private LibraryManagementService lms;

	@PostMapping("/login")
	public ModelAndView login(@RequestParam("username") String userName, @RequestParam("password") String userPass,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		if (login.check(userName, userPass)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", userName);
			mv.setViewName("index");
			List<List<String>> books = lms.books();
			mv.addObject("books", books);
		} else {
			System.out.println("login failed");
			mv.setViewName("login");
		}
		return mv;
	}

	@PostMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate();
		return "login";
	}

	@PostMapping("/delete")
	public ModelAndView delete(@RequestParam("link") String link) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		lms.deleteBook(link);
		List<List<String>> books = lms.books();
		mv.addObject("books", books);
		return mv;
	}

	@PostMapping("/edit")
	public ModelAndView edit(@RequestParam("link") String link) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("edit");
		List<String> book = lms.getBook(link);
		mv.addObject("book", book);
		List<String> authors = lms.authors();
		mv.addObject("authors", authors);
		return mv;
	}

	@PostMapping("/editDetails")
	public ModelAndView editDetails(@RequestParam("link") String link, @RequestParam("name") String name,
			@RequestParam("author") String author) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		lms.editBooks(link, name, author);
		List<List<String>> books = lms.books();
		mv.addObject("books", books);
		return mv;
	}

	@PostMapping("/back")
	public ModelAndView back() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		List<List<String>> books = lms.books();
		mv.addObject("books", books);
		return mv;
	}

	@PostMapping("/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		mv.addObject("date", date);
		List<String> authors = lms.authors();
		mv.addObject("authors", authors);
		mv.setViewName("add");
		return mv;
	}

	@PostMapping("/addDetails")
	public ModelAndView addDetails(@RequestParam("date") String date, @RequestParam("name") String name,
			@RequestParam("author") String author, @RequestParam("code") String code) {
		ModelAndView mv = new ModelAndView();
		if (lms.addbook(code, name, author, date)) {
			mv.setViewName("index");
			List<List<String>> books = lms.books();
			mv.addObject("books", books);
		} else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();
			String currDate = dtf.format(now);
			mv.addObject("date", currDate);
			List<String> authors = lms.authors();
			mv.addObject("authors", authors);
			mv.setViewName("add");
		}
		return mv;
	}
}
