import java.io.*;
import java.util.*;

public class Main {

	static int N, rCnt, gCnt, bCnt, rgCnt;
	static boolean[][] check;
	static char[][] board;

	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static boolean InRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static void DFS(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (InRange(nx, ny) && !check[nx][ny] && board[x][y] == board[nx][ny]) {
				check[nx][ny] = true;
				DFS(nx, ny);
			}
		}
	}

	static void DFS2(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (InRange(nx, ny) && !check[nx][ny] && (board[nx][ny] == 'R' || board[nx][ny] == 'G')) {
				check[nx][ny] = true;
				DFS2(nx, ny);
			}
		}
	}

	static void BFS(int x, int y) {
		ArrayDeque<Pair> q = new ArrayDeque<Pair>();
		q.offer(new Pair(x, y));

		while (!q.isEmpty()) {
			Pair p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (InRange(nx, ny) && !check[nx][ny] && board[p.x][p.y] == board[nx][ny]) {
					check[nx][ny] = true;
					q.offer(new Pair(nx, ny));
				}
			}
		}
	}
	
	static void BFS2(int x, int y) {
		ArrayDeque<Pair> q = new ArrayDeque<Pair>();
		q.offer(new Pair(x, y));

		while (!q.isEmpty()) {
			Pair p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (InRange(nx, ny) && !check[nx][ny] && (board[nx][ny] == 'R' || board[nx][ny] == 'G')) {
					check[nx][ny] = true;
					q.offer(new Pair(nx, ny));
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		check = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = s.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!check[i][j]) {
					check[i][j] = true;
					if (board[i][j] == 'R') {
						rCnt++;
					} else if (board[i][j] == 'G') {
						gCnt++;
					} else {
						bCnt++;
					}
					//DFS(i, j);
					BFS(i, j);
				}
			}
		}
		check = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!check[i][j] && (board[i][j] == 'R' || board[i][j] == 'G')) {
					check[i][j] = true;
					//DFS2(i, j);
					BFS2(i, j);
					rgCnt++;
				}
			}
		}

		sb.append(rCnt + gCnt + bCnt).append(" ").append(rgCnt + bCnt);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
