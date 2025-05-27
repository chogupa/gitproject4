package com.example.demo.form;

import java.util.ArrayList;
import java.util.Collections;

public class Minesweeper {

	private int masu;
	private int masume;
	private int mine;

	public Minesweeper(int masu) {
		this.masu=masu;
		this.masume = masu*masu;
		this.mine = (int)this.masume / 6;
	}

	public ArrayList<Integer> generateMinesweeper() {
		ArrayList<Integer> list = new ArrayList<Integer>();

		//1～masumeの整数値を持つリストを用意
		for (int i = 0; i < this.masume; i++) {
			list.add(i);
		}
		//        リストをシャッフル
		Collections.shuffle(list);
//地雷のあるマスのIDリストを用意
		ArrayList<Integer> list2 = new ArrayList<Integer>();

//		ランダムに地雷のあるマスを選んでリストに入れる。
		for (int i = 0; i < this.mine; i++) {
			list2.add(list.get(i));
		}
//		地雷のあるマスのIDリストを返す。
		return list2;

	}

	public ArrayList<Integer> MineCount(ArrayList<Integer> list) {
//		指定されたマス目の二次元配列を作成
		boolean[][] mineField = new boolean[this.masu][this.masu];
//		地雷のあるマスをtrueに
		for (int i = 0; i < this.mine; i++) {
			int index = list.get(i)-1;
			int y = (int) index / this.masu;
			int x = index - y * this.masu;
			mineField[y][x] = true;
		}
		ArrayList<Integer> countList = new ArrayList<Integer>();
//		マスひとつずつ調べる
		for (int y = 0; y < this.masu; y++) {
			for (int x = 0; x < this.masu; x++) {
				int count = 0;
//				周囲のマスに地雷があるか調べる
				for (int dy = -1; dy <= 1; dy++) {
					for (int dx = -1; dx <= 1; dx++) {
						if (dy == 0 && dx == 0)
							continue; // 自分自身は除外

						int ny = y + dy;
						int nx = x + dx;

						// 範囲チェック
						if (ny >= 0 && ny < this.masu && nx >= 0 && nx < this.masu) {
//							周囲のマスに地雷があればカウント
							if (mineField[ny][nx]) {
								count++;
							}
						}
					}

				}
//				ID順のリストに地雷の数をセット
				countList.add(count);
			}

		}
		return countList;

	}
}
