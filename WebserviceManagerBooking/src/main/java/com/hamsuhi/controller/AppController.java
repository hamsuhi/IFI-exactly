package com.hamsuhi.controller;
/*
 * Handling the view calls, coming from Our AngularJS based Front-end
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	
	@RequestMapping("/")
	public String home(ModelMap modal) {
		modal.addAttribute("title", "CRUD example");
		return "index"; // trả về file index.jsp
	}
	
//	@RequestMapping("/")
//	public ModelAndView home(ModelAndView view) {
//		view.setViewName("index");
//		return view;
//	}

}
