package com.ajay.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ajay.service.LoginService;
import com.ajay.service.SearchService;

@Controller
public class AppController {

	@Autowired
	private LoginService login;
	
	@Autowired
	private SearchService searchService;

	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("userName") String userName, @RequestParam("userPass") String userPass,
			HttpServletRequest request, HttpServletResponse response) {
		

		searchService.loadFiles();
		List<String> colors = searchService.getColors();
		ModelAndView mv = new ModelAndView();
		mv.addObject("colors",colors);
		
		if (login.check(userName, userPass)) {
			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			mv.setViewName("success");
		}else {
			mv.setViewName("index");
		}
		return mv;
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		session.removeAttribute("userName");
		session.invalidate();
		return "index";
	}

	@RequestMapping("/search")
	public ModelAndView search(@RequestParam("color") String color, @RequestParam("size") String size,
			@RequestParam("gender") String gender, @RequestParam("sortBy") String sortBy, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		List<List<String>> result = searchService.search(color, size, gender, sortBy);
		if(result == null || result.isEmpty()) {
			mv.setViewName("no-result");
		} else {
			mv.addObject("result", result);
			mv.setViewName("search-result");			
		}
		return mv;
	}
	
	@RequestMapping("/back")
	public ModelAndView back(HttpServletRequest request, HttpServletResponse response) {
		
		searchService.loadFiles();
		List<String> colors = searchService.getColors();
		ModelAndView mv = new ModelAndView();
		mv.addObject("colors",colors);
		mv.setViewName("success");
		return mv;
	}
}
