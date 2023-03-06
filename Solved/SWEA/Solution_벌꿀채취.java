import java.io.*;
import java.util.*;

public class Solution {

	static int N, M, C, res, board[][], profit[][];

	static void process() {
		makeProfit();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				combination(i, j + M, 1, profit[i][j]);
			}
		}
	}

	static void combination(int x, int y, int cnt, int sum) {

		if (cnt == 2) {
			res = Math.max(res, sum);
			return;
		}

		for (int i = x; i < N; i++) {
			for (int j = y; j <= N - M; j++) {
				combination(i, j, cnt + 1, sum + profit[i][j]);
			}
			y = 0;
		}
	}

	static void makeProfit() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				Subs(i, j, 0, 0, 0);
			}
		}

	}

	static void Subs(int i, int j, int cnt, int sum, int totalSum) {

		if (sum > C) {
			return;
		}
		
		if (cnt == M) {
			profit[i][j - M] = Math.max(profit[i][j - M], totalSum);
			return;
		}
		
		Subs(i, j + 1, cnt + 1, sum + board[i][j], totalSum + board[i][j] * board[i][j]);
		Subs(i, j + 1, cnt + 1, sum, totalSum);

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			profit = new int[N][N];
			board = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			res = 0;
			process();
			
			sb.append("#").append(tc).append(" ").append(res).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}