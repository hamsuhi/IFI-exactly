/*
 * Handling the view calls, coming from Our AngularJS based Front-end
 */
package com.hamsuhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@RequestMapping("/")
	public ModelAndView allTemplate(ModelAndView view) {
		view.setViewName("index");//tro den file index.jsp
		return view;
	}
	
	@RequestMapping("/partials/{page}")
		public String partialHandler(@PathVariable("page") final String page) {
			return page;
	}
	
}
