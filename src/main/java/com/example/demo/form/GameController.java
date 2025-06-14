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
		this.dao = dao;
	}

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping("game")
	public String game(Model model) {
		int masume = 5;
		//		再読み込み後にbombがずれないようにデータを削除
		dao.deleteAll();
		//		AUTOでidを挿入するとずれるので、こちらで入れるように変更
		for (int i = 1; i <= masume * masume; i++) {
			//		升目と同じ数のid分、行を挿入
			dao.insertDao(i);
		}
		Minesweeper minesweeper = new Minesweeper(masume);
		//		generateMinesweeperメソッドで地雷のあるマスのidをリスト化して呼び出す
		ArrayList<Integer> list = minesweeper.generateMinesweeper();
		for (int i = 0; i < list.size(); i++) {
			//		生成したidはひとつずれているので、それを＋１で修正
			long id = list.get(i) + 1;
			//		そのidの入った行を所得
			List<EntForm> list1 = dao.getOne(id);
			EntForm entformdb = list1.get(0);
			//		bombをセットしてアップデート
			entformdb.setBomb(1);
			dao.updateDao(id, entformdb);
		}
		//		周囲のマスに何個地雷があるかのカウントをすべてのマスで所得、listでさきほど生成した地雷のあるマスのidリストを渡す
		ArrayList<Integer> minecount = minesweeper.MineCount(list);
		for (int i = 0; i < minecount.size(); i++) {
			//			idは1からスタートなので+1して、その行を所得
			List<EntForm> list2 = dao.getOne((long) i + 1);
			EntForm entformdb = list2.get(0);
			//			地雷の数をセット
			entformdb.setCount(minecount.get(i));
			dao.updateDao((long) i + 1, entformdb);

		}
		//		ゲーム画面にデータベースを渡す
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

	//	指定されたidの行を返す
	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable Long id) {
		List<EntForm> list = dao.getOne(id);
		EntForm entformdb = list.get(0);
		model.addAttribute("form", entformdb);
		return "edit";
	}

	@RequestMapping("confirm/{id}")
	//	editでエラーがあるか判定
	public String confirm(@PathVariable Long id, @Validated Input input, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<EntForm> list = dao.getOne(id);
			EntForm entformdb = list.get(0);
			model.addAttribute("form", entformdb);
			model.addAttribute("input", input);

			return "edit_error";
		}
		return "confirm";
	}
	//	@RequestMapping("complete")
	//	public String complete(@PathVariable Long id,Input input,Model model) {
	//		EntForm entform = new EntForm();	
	//		entform.setComment(input.getComment());
	//		dao.updateDao(id,entform);
	//		return "game";
	//	}

	//	confirmでeditに戻るため
	@RequestMapping("back")
	public String back(@PathVariable Long id, Input input, Model model) {
		List<EntForm> list = dao.getOne(id);
		EntForm entformdb = list.get(0);
		model.addAttribute("form", entformdb);
		model.addAttribute("input", input);
		return "edit";
		//		back

	}

	//	コメントを編集し、アップロードして、ゲームに戻る。
	@RequestMapping("/regame/{id}")
	public String regame(@PathVariable Long id, Input input, Model model) {

		List<EntForm> list = dao.getOne(id);
		EntForm entformdb = list.get(0);
		entformdb.setComment(input.getComment());
		dao.updateDao(id, entformdb);
		List<EntForm> listMine = dao.getAll();
		model.addAttribute("dbList", listMine);
		System.out.println(input.getComment());
		return "game";
		//		regameの追加
	}

	//	ゲーム画面に戻る
	@RequestMapping("/regame2")
	public String regame2(Model model) {
		List<EntForm> listMine = dao.getAll();
		model.addAttribute("dbList", listMine);
		return "game";
	}
	//	regame2からidを除去

	@RequestMapping("/confirmreset")
	public String confirmreset() {
		return "confirmreset";
	}

}
