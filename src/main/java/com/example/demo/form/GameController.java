package com.example.demo.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
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
//		再読み込み後にidがずれないようにデータを削除
		dao.deleteAll();
//		AUTOでidを挿入するとずれるので、こちらで入れるように変更
		for(int i=1;i<=masume*masume;i++) {
			dao.insertDao(i);
		}
		Minesweeper minesweeper = new Minesweeper(masume);
		ArrayList<Integer>list = minesweeper.generateMinesweeper();
		for(int i=0;i<list.size();i++) {
			long id = list.get(i) + 1;
			List<EntForm> list1 = dao.getOne(id);
			EntForm entformdb = list1.get(0);
			entformdb.setBomb(1); // +1しなくてOK
			dao.updateDao(id, entformdb);
		}
		ArrayList<Integer> minecount = minesweeper.MineCount(list);
		
		for(int i=0;i<minecount.size();i++) {
			
			List<EntForm> list2= dao.getOne((long)i+1);
			EntForm entformdb=list2.get(0);
			entformdb.setCount(minecount.get(i));
			dao.updateDao((long)i+1, entformdb);
		
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
	public String edit(Model model,@PathVariable Long id) {
		List<EntForm> list=dao.getOne(id);
		EntForm entformdb=list.get(0);
		model.addAttribute("form",entformdb);
		return "edit";
	}
	
	@RequestMapping("confirm")

	public String confirm(@PathVariable Long id,@Validated Input input,BindingResult result,Model model) {
		if (result.hasErrors()) {
			List<EntForm> list=dao.getOne(id);
			EntForm entformdb=list.get(0);
			model.addAttribute("form",entformdb);
			model.addAttribute("input",input);
			return "edit_error";
		}
		return "confirm";
	}
	@RequestMapping("complete")
	public String complete(@PathVariable Long id,Input input,Model model) {
		EntForm entform = new EntForm();	
		entform.setComment(input.getComment());
		dao.updateDao(id,entform);
		return "game";
	}
	
	@RequestMapping("back")
	public String back(@PathVariable Long id,Input input,Model model) {
		List<EntForm> list=dao.getOne(id);
		EntForm entformdb=list.get(0);
		model.addAttribute("form",entformdb);
		model.addAttribute("input",input);
		return "edit";
//		back
		
	}
	
	@RequestMapping("regame")
		public String regame(Model model) {
		List<EntForm> listMine = dao.getAll();
		model.addAttribute("dbList", listMine);
		return "game";
//		regameの追加
	}

	
}

