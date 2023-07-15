import java.io.*;
import java.util.*;

public class Main {

	static int[][] board;
	static pair[][] destination;
	static int N, M, fuel, taxiX, taxiY, useFuel;
	static boolean[][] boardCheck;
	static boolean check;
	static StringBuilder sb = new StringBuilder();

	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };

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
		PriorityQueue<pair> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.x == o2.x) {
				return o1.y - o2.y;
			}
			return o1.x - o2.x;
		});

		q.offer(new pair(x, y));
		int len = 1;
		while (!q.isEmpty()) {
			pair p = q.poll();
			len--;

			if (board[p.x][p.y] == 2) {
				pq.offer(new pair(p.x, p.y));
			}

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (InRange(nx, ny) && !boardCheck[nx][ny] && board[nx][ny] != 1) {
					boardCheck[nx][ny] = true;
					q.offer(new pair(nx, ny));
				}
			}

			if (len == 0) {
				len = q.size();
				if (!pq.isEmpty()) {
					pair tempPair = pq.poll();
					MoveTaxi(tempPair.x, tempPair.y);
					if (M == 0) {
						check = true;
						return;
					}
					if (fuel <= 0) {
						return;
					}
					while (!q.isEmpty()) {
						q.poll();
					}
					while (!pq.isEmpty()) {
						pq.poll();
					}

					q.offer(new pair(taxiX, taxiY));
					len = 1;
					useFuel = 0;
					boardCheck = new boolean[N][N];
					continue;
				}
				fuel--;
			}

			if (fuel < 0) {
				return;
			}
		}
	}

	static void MoveTaxi(int x, int y) {
		int desX = destination[x][y].x;
		int desY = destination[x][y].y;

		boardCheck = new boolean[N][N];
		ArrayDeque<pair> q = new ArrayDeque<>();
		q.offer(new pair(x, y));
		int len = 1;
		while (!q.isEmpty()) {
			pair p = q.poll();
			len--;

			if (p.x == desX && p.y == desY) {
				board[x][y] = 0;
				M--;
				fuel += (useFuel * 2);
				taxiX = desX;
				taxiY = desY;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (InRange(nx, ny) && !boardCheck[nx][ny] && board[nx][ny] != 1) {
					boardCheck[nx][ny] = true;
					q.offer(new pair(nx, ny));
				}
			}

			if (len == 0) {
				fuel--;
				useFuel++;
				len = q.size();
			}

			if (fuel < 0) {
				return;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());

		board = new int[N][N];
		destination = new pair[N][N];
		boardCheck = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken());
		int startY = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dex = Integer.parseInt(st.nextToken());
			int dey = Integer.parseInt(st.nextToken());
			board[x - 1][y - 1] = 2;
			destination[x - 1][y - 1] = new pair(dex - 1, dey - 1);
		}

		boardCheck[startX - 1][startY - 1] = true;
		BFS(startX - 1, startY - 1);

		if (check) {
			sb.append(fuel);
		} else {
			sb.append("-1");
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}