package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Hello world!
 *
 */
@RestController
@SpringBootApplication
@ComponentScan(basePackages = "com.demo")
public class App extends SpringBootServletInitializer{
	
	private static final String PREFIX = "templates/";
	
	@RequestMapping("/")
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index11");
		return mav;
	}
	@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
			// TODO Auto-generated method stub
			return builder.sources(App.class);
		}
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
