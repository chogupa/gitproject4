package com.example.demo.form;

import java.util.ArrayList;
import java.util.Collections;

public class Minesweeper {

	private int masume;
	private int mine;
	
	public void Minesweeper(int masume) {
		this.masume = masume;
		this.mine = masume/6;
	}
	
	public ArrayList<Integer> Minesweeper() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		 
        //1～masumeの整数値を持つリストを用意
        for(int i = 1 ; i <= this.masume ; i++) {
                list.add(i);
        }
        System.out.println(list);
//        リストをシャッフル
        Collections.shuffle(list);
        
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        
        for(int i = 1 ; i <= this.mine ; i++) {
            list2.add(list.get(i));
    }
        System.out.println(list2);
		return list2;
		
	}
}
