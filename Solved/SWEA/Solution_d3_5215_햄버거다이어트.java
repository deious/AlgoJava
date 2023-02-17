package ex;

import java.io.*;
import java.util.*;

public class Solution_d3_20230216_햄버거다이어트_서울_20반_김한성 {

	static int T, N, L, answer = 0;
	static int[] taste, cal;

	static void Perm(int cnt, int start, int t, int c) {
		if (c > L) {
			return;
		}
		
		if (answer < t) {
			answer = t;
		}

		if (cnt == N) {
			return;
		}

		Perm(cnt + 1, start + 1, t + taste[start], c + cal[start]);
		Perm(cnt + 1, start + 1, t, c);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			taste = new int[N];
			cal = new int[N];
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				taste[j] = Integer.parseInt(st.nextToken());
				cal[j] = Integer.parseInt(st.nextToken());
			}
			
			answer = 0;

			Perm(0, 0, 0, 0);
			sb.append("#").append(i).append(" ").append(answer).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
