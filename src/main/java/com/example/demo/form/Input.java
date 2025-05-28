package com.example.demo.form;

import jakarta.validation.constraints.Size;

public class Input {
	
	private int id;
	private int flag;
	private String comment;
	private int bomb;
	@Size(max=1,message="ゲームオーバー")
	private int count;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getBomb() {
		return bomb;
	}
	public void setBomb(int bomb) {
		this.bomb = bomb;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	

}
