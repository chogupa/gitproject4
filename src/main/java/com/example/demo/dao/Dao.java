package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EntForm;

@Repository
public class Dao {

	private final JdbcTemplate db;

	public Dao(JdbcTemplate db) {
		this.db = db;
	}
//	データのアップロード
	public void insertDao(int id) {
			db.update("INSERT INTO minesweeper (id,flag,comment,bomb,count) VALUES(?,0,'コメントを入力できます',0,0)",id);
	}
	
	public List<EntForm> getAll() {
		String sql = "SELECT * FROM minesweeper";

		//データベースから取り出したデータをresultDB1に入れる
		List<Map<String, Object>> resultDb1 = db.queryForList(sql);

		//画面に表示しやすい形のList(resultDB2)を用意
		List<EntForm> resultDb2 = new ArrayList<EntForm>();

		//1件ずつピックアップ
		for (Map<String, Object> result1 : resultDb1) {

			//データ1件分を1つのまとまりとしたEntForm型の「entformdb」を生成
			EntForm entformdb = new EntForm();

			//id、nameのデータをentformdbに移す
			entformdb.setId((int) result1.get("id"));
			entformdb.setFlag((int) result1.get("flag"));
			entformdb.setComment((String) result1.get("comment"));
			entformdb.setBomb((int) result1.get("bomb"));
			entformdb.setCount((int) result1.get("count"));

			
			
			//移し替えたデータを持ったentformdbを、resultDB2に入れる
			resultDb2.add(entformdb);
		}

		//Controllerに渡す
		return resultDb2;	
	}
	
	public void updateDao(Long id,EntForm entform) {
		db.update("UPDATE minesweeper SET flag = ?, comment = ?,bomb = ?, count = ? WHERE id = ?", entform.getFlag(),entform.getComment(),entform.getBomb() ,entform.getCount(),id);
	}

	
	public List<EntForm> getOne(Long id) {
		List<Map<String, Object>> resultDb1 = db.queryForList("SELECT * FROM minesweeper where id=?", id);
		List<EntForm> resultDb2 = new ArrayList<EntForm>();

		//1件ずつピックアップ
		for (Map<String, Object> result1 : resultDb1) {
			//データ1件分を1つのまとまりとするので、EntForm型の「entformdb」を生成
			EntForm entformdb = new EntForm();
			//id、nameのデータをentformdbに移す
			entformdb.setId((int) result1.get("id"));
			entformdb.setFlag((int) result1.get("flag"));
			entformdb.setComment((String) result1.get("comment"));
			entformdb.setBomb((int) result1.get("bomb"));
			entformdb.setCount((int) result1.get("count"));
			//移し替えたデータを持ったentformdbを、resultDB2に入れる
			resultDb2.add(entformdb);
		}

		//Controllerに渡す
		return resultDb2;

	}
	
	public void deleteAll() {
		db.update("delete from minesweeper");
	}
}
