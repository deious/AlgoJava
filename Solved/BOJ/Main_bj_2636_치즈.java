import java.io.*;
import java.util.*;

public class Main {

	static int[][] board, tBoard;
	static int N, M, time, cheeseCnt = 0, beforeCnt = 0;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static boolean[][] check;

	static class pair {
		int x, y;

		pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static boolean InRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	static void BFS(int x, int y) {
		ArrayDeque<pair> q = new ArrayDeque<>();
		q.offer(new pair(x, y));
		check[x][y] = true;
		time++;

		tBoard = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tBoard[i][j] = board[i][j];
			}
		}

		while(!q.isEmpty()) {
			pair p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if (InRange(nx, ny) && !check[nx][ny] && board[nx][ny] == 0) {
					check[nx][ny] = true;
					q.offer(new pair(nx, ny));
				} else if (InRange(nx, ny) && !check[nx][ny] && board[nx][ny] == 1) {
					tBoard[nx][ny] = 0;
				}
			}
		}

		int cCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] = tBoard[i][j];
				if (board[i][j] == 1) {
					cCnt++;
				}
			}
		}
		
		beforeCnt = cheeseCnt;
		cheeseCnt = cCnt;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		check = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1) {
					cheeseCnt++;
				}
			}
		}
		
		boolean flag = false;
		while(cheeseCnt != 0) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[i][j] == 0 && !check[i][j]) {
						BFS(i, j);
						flag = true;
						break;
					}
				}
				if (flag) {
					break;
				}
			}
			check = new boolean[N][M];
		}
		sb.append(time).append("\n").append(beforeCnt);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
