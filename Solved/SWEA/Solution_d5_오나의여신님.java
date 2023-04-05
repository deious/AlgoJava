import java.io.*;
import java.util.*;

public class Solution {

	static int N, M, gx, gy, hAnswer = 2100000000, dAnswer = 2100000000;

	static char board[][];
	static int devil[][];
	static int goddess[][];
	static boolean check[][];
	
	static ArrayDeque<pair> ad;

	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };

	static class pair {
		int x, y, time;

		pair(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	static boolean InRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	static void BFSH(int x, int y) {
		ArrayDeque<pair> q = new ArrayDeque<pair>();
		q.offer(new pair(x, y, 0));
		check = new boolean[N][M];
		check[x][y] = true;

		while (!q.isEmpty()) {
			pair p = q.poll();
			if (p.x == gx && p.y == gy) {
				hAnswer = p.time;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (InRange(nx, ny) && !check[nx][ny] && board[nx][ny] != 'X' && board[nx][ny] != '*' && devil[nx][ny] > p.time + 1) {
					check[nx][ny] = true;
					q.offer(new pair(nx, ny, p.time + 1));
				}
			}
		}
	}

	static void BFSD() {
		ArrayDeque<pair> q = new ArrayDeque<pair>();
		check = new boolean[N][M];
		while(!ad.isEmpty()) {
			pair p = ad.poll();
			check[p.x][p.y] = true;
			devil[p.x][p.y] = 0;
			q.offer(p);
		}
		devil[gx][gy] = 210000000;

		while (!q.isEmpty()) {
			pair p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (InRange(nx, ny) && !check[nx][ny] && board[nx][ny] != 'X' && board[nx][ny] != 'D') {
					devil[nx][ny] = p.time + 1;
					check[nx][ny] = true;
					q.offer(new pair(nx, ny, p.time + 1));
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

		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			board = new char[N][M];
			devil = new int[N][M];
			goddess = new int[N][M];

			hAnswer = 2100000000;
			dAnswer = 2100000000;

			ad = new ArrayDeque<pair>();
			
			int dx = 0, dy = 0, hx = 0, hy = 0;
			for (int j = 0; j < N; j++) {
				String s = br.readLine();
				for (int k = 0; k < M; k++) {
					board[j][k] = s.charAt(k);
					if (board[j][k] == 'D') {
						gx = j;
						gy = k;
					} else if (board[j][k] == 'S') {
						hx = j;
						hy = k;
					} else if (board[j][k] == '*') {
						ad.offer(new pair(j, k, 0));
					}
				}
			}
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					devil[j][k] = 2100000000;
				}
			}

			if (ad.size() != 0)
				BFSD();
			BFSH(hx, hy);

			sb.append("#").append(i).append(" ");
			if (hAnswer != 2100000000) {
				sb.append(hAnswer).append("\n");
			} else {
				sb.append("GAME OVER").append("\n");
			}
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
