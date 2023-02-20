package ex;

import java.io.*;
import java.util.*;

public class Main_bj_2630_색종이만들기_서울_20반_김한성 {
	static int white = 0;
	static int green = 0;
	static int[][] spaces;

	static void cut(int r, int c, int size) {

		int sum = 0;
		for (int i = r, rEnd = r + size; i < rEnd; i++) {
			for (int j = c, cEnd = c + size; j < cEnd; j++) {
				sum += spaces[i][j];
			}
		}
		
		if (sum == size * size) {
			green++;
		}else if(sum == 0) {
			white++;
		}else {
			int half = size / 2;
			cut(r, c, half);
			cut(r, c + half, half);
			cut(r + half, c, half);
			cut(r + half, c + half, half);
		}
		return;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		spaces = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				spaces[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cut(0,0,n);
		sb.append(white).append("\n").append(green);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}

