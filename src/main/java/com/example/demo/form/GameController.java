package com.example.demo.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class GameController {

	@RequestMapping("start")
	public String start() {
		return "start";
	}
//	test
}
