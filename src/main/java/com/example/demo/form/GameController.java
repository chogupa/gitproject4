package com.example.demo.form;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class GameController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}
	@RequestMapping("game")
	public String game(Minesweeper minesweeper,Model model) {
		
		model.addAttribute("Minesweeper",minesweeper);
		
		return "game";
	}
	@GetMapping("game")
	public String gameget() {
		return "/game";
	}
	@PostMapping("game")
	public String gamepost(@Valid @ModelAttribute("input") Input input ,BindingResult result, Model model)  {
//		if(result.hasError()) {
//			return "game";
//		}
		
		return "/game";
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
