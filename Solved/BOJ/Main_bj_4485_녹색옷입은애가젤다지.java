import java.io.*;
import java.util.*;

public class Main {

	static int[][] dp;
	static int[][] board;
	static int N;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static class pair {
		int x, y;

		pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static boolean InRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static void BFS(int x, int y) {
		ArrayDeque<pair> q = new ArrayDeque<>();
		q.offer(new pair(x, y));

		while (!q.isEmpty()) {
			pair p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (InRange(nx, ny)) {
					if (dp[nx][ny] > dp[p.x][p.y] + board[nx][ny]) {
						dp[nx][ny] = dp[p.x][p.y] + board[nx][ny];
						q.offer(new pair(nx, ny));
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = 1;
		int tc = 0;
		while (true) {
			tc++;
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			board = new int[N][N];
			dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dp[i], 2100000000);
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dp[0][0] = board[0][0];
			BFS(0, 0);
			sb.append("Problem ").append(tc).append(": ").append(dp[N - 1][N - 1]).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}