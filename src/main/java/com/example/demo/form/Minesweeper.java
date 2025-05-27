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
	
//	boolean[][] mineField = {
//            {false, false, false, false, false},
//            {false, true,  false, false, false},
//            {false, false, false, true,  false},
//            {false, false, false, false, false},
//            {false, false, false, false, false},
//        };
//
//        // 地雷をカウントしたい位置（例：2,2）
//        int y = 2;
//        int x = 2;
//
//        int count = 0;
//
//        // 8方向を調べる
//        for (int dy = -1; dy <= 1; dy++) {
//            for (int dx = -1; dx <= 1; dx++) {
//                if (dy == 0 && dx == 0) continue; // 自分自身は除外
//
//                int ny = y + dy;
//                int nx = x + dx;
//
//                // 範囲チェック
//                if (ny >= 0 && ny < 5 && nx >= 0 && nx < 5) {
//                    if (mineField[ny][nx]) {
//                        count++;
//                    }
//                }
//            }
//        }
//
//        System.out.println("周りの地雷の数: " + count);
//    }
}
