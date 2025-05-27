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
	@RequestMapping("gameclear")
	public String gameclear() {
		return "gameclear";
	}
	@RequestMapping("gameover")
	public String gameover() {
		return "gameover";
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
