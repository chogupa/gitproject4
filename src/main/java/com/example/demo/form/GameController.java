package com.example.demo.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.Dao;
import com.example.demo.entity.EntForm;

@Controller

public class GameController {

	private final Dao dao;
	
	public GameController(Dao dao) {
		this.dao=dao;
	}
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	
	
	
	@RequestMapping("game")
	public String game(Model model) {
		int masume = 5;
		for(int i=0;i<masume*masume;i++) {
			dao.insertDao();
		}
		Minesweeper minesweeper = new Minesweeper(masume);
		ArrayList<Integer>list = minesweeper.generateMinesweeper();
		for(int i=0;i<list.size();i++) {
			List<EntForm> list1 = dao.getOne((long)list.get(i));
			EntForm entformdb=list1.get(0);
		
			entformdb.setBomb(entformdb.getBomb()+1);
			dao.updateDao((long) i, entformdb);
		
		}
		ArrayList<Integer> minecount = minesweeper.MineCount(list);
		
		for(int i=0;i<minecount.size();i++) {
			
			List<EntForm> list2= dao.getOne((long)i+1);
			EntForm entformdb=list2.get(0);
			entformdb.setCount(minecount.get(i));
			dao.updateDao((long)i, entformdb);
		
		}
		List<EntForm> listMine = dao.getAll();
		model.addAttribute("dbList", listMine);
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
