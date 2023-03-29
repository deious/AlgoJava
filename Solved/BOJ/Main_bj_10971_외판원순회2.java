import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static boolean[] check;
	static int[][] board;
	static int answer = 2100000000;

	static void DFS(int now, int cost) {
		if (Check()) {
			if (board[now][0] != 0) {
				answer = Math.min(answer, cost + board[now][0]);
			}
			return;
		}

		for (int i = 1; i < N; i++) {
			if (!check[i] && board[now][i] != 0) {
				check[i] = true;
				DFS(i, cost + board[now][i]);
				check[i] = false;
			}
		}
	}

	static boolean Check() {
		for (int i = 0; i < N; i++) {
			if (!check[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		check = new boolean[N];
		check[0] = true;
		DFS(0, 0);

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}