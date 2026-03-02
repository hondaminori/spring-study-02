package com.example.demo.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController2 {
	@GetMapping("/hello")
	public String hello2() {
		return "index";
	}
}