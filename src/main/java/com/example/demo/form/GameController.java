package com.example.demo.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class GameController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}
	@RequestMapping("game")
	public String game() {
		return "game";
	}
	@RequestMapping("finish")
	public String finish() {
		return "finish";
	}
	@RequestMapping("edit")
	public String edit() {
		return "edit";
	}
	@RequestMapping("confirm")
	public String confirm() {
		return "confirm";
	}

	
}
