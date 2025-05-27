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
		this.mine = masume / 6;
	}

	public ArrayList<Integer> generateMinesweeper() {
		ArrayList<Integer> list = new ArrayList<Integer>();

		//1～masumeの整数値を持つリストを用意
		for (int i = 0; i < this.masume; i++) {
			list.add(i);
		}
		System.out.println(list);
		//        リストをシャッフル
		Collections.shuffle(list);

		ArrayList<Integer> list2 = new ArrayList<Integer>();

		for (int i = 0; i < this.mine; i++) {
			list2.add(list.get(i));
		}
		System.out.println(list2);
		return list2;

	}

	private ArrayList<Integer> MineCount(ArrayList<Integer> list) {
		boolean[][] mineField = new boolean[this.masu][this.masu];
		ArrayList<Integer> countList = new ArrayList<Integer>();
		for (int i = 0; i < this.mine; i++) {
			int index = list.get(i)-1;
			int y = (int) index / this.masu;
			int x = index - y * this.masu;
			mineField[y][x] = true;
		}

		for (int y = 0; y < this.masu; y++) {
			for (int x = 0; x < this.masu; x++) {
				int count = 0;
				for (int dy = -1; dy <= 1; dy++) {
					for (int dx = -1; dx <= 1; dx++) {
						if (dy == 0 && dx == 0)
							continue; // 自分自身は除外

						int ny = y + dy;
						int nx = x + dx;

						// 範囲チェック
						if (ny >= 0 && ny < this.masu && nx >= 0 && nx < this.masu) {
							if (mineField[ny][nx]) {
								count++;
							}
						}
					}

				}
				countList.add(count);
			}

		}
		return countList;

	}
}
