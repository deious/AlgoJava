import java.io.*;
import java.util.*;

public class Main {

	static int[] dice;
	static int[][] board;
	static boolean[][] check;

	static int N, M, K, x, y, answer = 0, dir;

	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };

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

	static void BFS(int x, int y, int num) {
		ArrayDeque<pair> q = new ArrayDeque<pair>();
		q.offer(new pair(x, y));
		
		check = new boolean[N][M];
		check[x][y] = true;
		
		int cnt = 0;
		while(!q.isEmpty()) {
			pair p = q.poll();
			cnt++;
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if (InRange(nx, ny) && !check[nx][ny] && board[nx][ny] == num) {
					check[nx][ny] = true;
					q.offer(new pair(nx, ny));
				}
			}
		}
		
		answer += num * cnt;
	}
	
	static void Move(int d) {
		if (InRange(x + dx[d], y + dy[d])) {
			if (d == 0) {					// 동
				int temp = dice[5];
				dice[5] = dice[1];
				dice[1] = dice[4];
				dice[4] = dice[3];
				dice[3] = temp;
			} else if (d == 1) {			// 남
				int temp = dice[1];
				dice[1] = dice[0];
				dice[0] = dice[3];
				dice[3] = dice[2];
				dice[2] = temp;
			}else if (d == 2) {			// 서
				int temp = dice[5];
				dice[5] = dice[3];
				dice[3] = dice[4];
				dice[4] = dice[1];
				dice[1] = temp;
			} else {						// 북
				int temp = dice[1];
				dice[1] = dice[2];
				dice[2] = dice[3];
				dice[3] = dice[0];
				dice[0] = temp;
			}

			x += dx[d];
			y += dy[d];

			BFS(x, y, board[x][y]);
		} else {
			dir = (d + 2) % 4;
			Move(dir);
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		x = 0;
		y = 0;

		board = new int[N][M];
		dice = new int[] { 2, 1, 5, 6, 4, 3 };

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dir = 0;
		for (int i = 0; i < K; i++) {				//동남서북
			Move(dir);
			if (dice[3] > board[x][y]) {
				dir = (dir + 1) % 4;
			} else if (dice[3] < board[x][y]) {
				dir = (dir + 4 - 1) % 4;
			} 
		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}