package com.lyh.xiaobiao.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/main")
public class MainController {
	
	@RequestMapping("toHome")
	public ModelAndView home(){
		return new ModelAndView("/html/home.html");
	}
	
	@RequestMapping("sidebar")
	public ModelAndView sidebar(){
		return new ModelAndView("/html/sidebar.html");
	}
	@RequestMapping("navbar")
	public ModelAndView navbar(){
		return new ModelAndView("/html/navbar.html");
	}
}
