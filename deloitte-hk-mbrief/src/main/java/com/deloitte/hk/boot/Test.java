package com.deloitte.hk.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Test {
	
	@RequestMapping("/test")
    public ModelAndView  test() {
		return new ModelAndView("test");
    }

}
