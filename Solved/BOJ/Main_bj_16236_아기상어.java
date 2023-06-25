import java.io.*;
import java.util.*;

public class Main {

	static int[][] board;
	static int N, total, sharkSize = 2, eatCnt, answer;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static boolean[][] check;

	static class pair {
		int x, y, time;

		pair(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	static boolean InRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static void BFS(int x, int y, int time) {
		ArrayDeque<pair> q = new ArrayDeque<>();
		PriorityQueue<pair> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.x == o2.x) {
				return o1.y - o2.y;
			}
			return o1.x - o2.x;
		});

		q.offer(new pair(x, y, 0));
		board[x][y] = 0;

		int len = 1;
		while (!q.isEmpty()) {
			if (total == 0) {
				return;
			}

			pair p = q.poll();
			len--;

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				int t = p.time;

				if (InRange(nx, ny) && !check[nx][ny]) {
					if (board[nx][ny] == 0) {
						check[nx][ny] = true;
						q.offer(new pair(nx, ny, t + 1));
					} else if (board[nx][ny] < sharkSize) {
						check[nx][ny] = true;
						q.offer(new pair(nx, ny, t + 1));
						pq.offer(new pair(nx, ny, t + 1));
					} else if (board[nx][ny] == sharkSize) {
						check[nx][ny] = true;
						q.offer(new pair(nx, ny, t + 1));
					}
				}
			}

			if (len == 0) {
				int a = 0, b = 0;
				if (!pq.isEmpty()) {
					pair tp = pq.poll();
					a = tp.x;
					b = tp.y;
					board[tp.x][tp.y] = 0;

					total--;
					answer += tp.time;
					if (++eatCnt == sharkSize) {
						sharkSize++;
						eatCnt = 0;
					}
					
					while (!pq.isEmpty()) {
						pq.poll();
					}

					while (!q.isEmpty()) {
						q.poll();
					}

					len = 1;
					q.offer(new pair(a, b, 0));
					check = new boolean[N][N];
					check[a][b] = true;
					
				} else {
					len = q.size();
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

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		check = new boolean[N][N];

		int startX = 0, startY = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] != 0) {
					total++;
				}

				if (board[i][j] == 9) {
					startX = i;
					startY = j;
				}
			}
		}

		total--;
		check[startX][startY] = true;
		if (total != 0) {
			BFS(startX, startY, 0);
		}
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}