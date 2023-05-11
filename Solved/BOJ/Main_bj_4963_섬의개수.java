import java.io.*;
import java.util.*;

class PairXY {
	int x, y;

	PairXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int[][] board;
	static boolean[][] check;
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 };
	static int w, h;

	static boolean InRange(int x, int y) {
		return x >= 0 && x < h && y >= 0 && y < w;
	}

	static void BFS(int x, int y) {
		ArrayDeque<PairXY> q = new ArrayDeque<PairXY>();
		q.offer(new PairXY(x, y));

		while (!q.isEmpty()) {
			PairXY p = q.poll();

			for (int i = 0; i < 8; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (InRange(nx, ny) && !check[nx][ny] && board[nx][ny] == 1) {
					check[nx][ny] = true;
					q.offer(new PairXY(nx, ny));
				}
			}
		}
	}

	static void DFS(int x, int y) {
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (InRange(nx, ny) && !check[nx][ny] && board[nx][ny] == 1) {
				check[nx][ny] = true;
				DFS(nx, ny);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			int answer = 0;

			if (w == 0 && h == 0) {
				break;
			}
			board = new int[h][w];
			check = new boolean[h][w];

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (!check[i][j] && board[i][j] == 1) {
						check[i][j] = true;
						//BFS(i, j);
						DFS(i, j);
						answer++;
					}
				}
			}
			sb.append(answer).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}