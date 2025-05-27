package com.example.demo.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class GameController {

	@RequestMapping("start")
	public String start() {
		return "start";
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
	@RequestMapping("complete")
	public String complete() {
		return "complete";
	}
	
}
