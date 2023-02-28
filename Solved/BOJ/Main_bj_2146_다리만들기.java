import java.io.*;
import java.util.*;

public class Main {

	static int[][] board;
	static boolean[][] check;
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };
	static int N, idx = 1, minMove = 2100000000;

	static class pair {
		int x, y;

		pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class data {
		int x, y, moveCnt, color;

		data(int x, int y, int moveCnt, int color) {
			this.x = x;
			this.y = y;
			this.moveCnt = moveCnt;
			this.color = color;
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
			board[p.x][p.y] = idx;
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if (InRange(nx, ny) && !check[nx][ny] && board[nx][ny] == 1) {
					check[nx][ny] = true;
					q.offer(new pair(nx, ny));
				}
			}
		}
	}
	
	static void BFS2(int x, int y, int moveCnt, int color) {
		ArrayDeque<data> q = new ArrayDeque<>();
		q.offer(new data(x, y, 0, color));

		while (!q.isEmpty()) {
			data d = q.poll();
			
			if (d.moveCnt >= minMove) {
				return;
			}
			
			if (board[d.x][d.y] != 0 && board[d.x][d.y] != color) {
				if (d.moveCnt < minMove) {
					minMove = d.moveCnt;
				}
				
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				
				if (InRange(nx, ny) && !check[nx][ny] && board[nx][ny] != color) {
					check[nx][ny] = true;
					q.offer(new data(nx, ny, d.moveCnt + 1, color));
				}
			}
		}
	}
	
	static void DFS(int x, int y, int move, int color) {
		if (move >= minMove) {
			return;
		}
		
		if (board[x][y] != 0 && board[x][y] != color) {
			if (move < minMove) {
				minMove = move;
				return;
			}
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (InRange(nx, ny) && !check[nx][ny] && board[nx][ny] != color) {
				check[nx][ny] = true;
				DFS(nx, ny, move + 1, color);
				check[nx][ny] = false;
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
		board = new int[N][N];
		check = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!check[i][j] && board[i][j] == 1) {
					check[i][j] = true;
					BFS(i, j);
					idx++;
				}
			}
		}
		
		//check = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				check = new boolean[N][N];
				if (!check[i][j] && board[i][j] != 0) {
					check[i][j] = true;
					//DFS(i, j, 0, board[i][j]);
					BFS2(i, j, 0, board[i][j]);
				}
			}
		}
		sb.append(minMove - 1);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
