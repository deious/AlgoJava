import java.awt.*;
import java.io.*;
import java.util.*;

class tuple {
	int x, y, time;

	tuple(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getTime() {
		return this.time;
	}
}

class Main {

	static int N, M, twoCnt, zeroCnt = 0, answer = 2100000000;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] board;
	static boolean[] check;
	static boolean[][] bCheck;
	static ArrayList<Point> v;

	static boolean InRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static void Perm(int cnt, int start) {
		if (cnt == M) {
			BFS();
			return;
		}

		for (int i = start; i < twoCnt; i++) {
			if (check[i]) {
				continue;
			}

			check[i] = true;
			Perm(cnt + 1, i + 1);
			check[i] = false;
		}
	}

	static void BFS() {
		ArrayDeque<tuple> q = new ArrayDeque<tuple>();
		bCheck = new boolean[N][N];
		for (int i = 0; i < twoCnt; i++) {
			if (check[i]) {
				q.add(new tuple(v.get(i).x, v.get(i).y, 0));
				bCheck[v.get(i).x][v.get(i).y] = true;
			}
		}

		int lastTime = 0;
		int tempZero = 0;

		while (!q.isEmpty()) {
			tuple t = q.poll();
			int x = t.x;
			int y = t.y;
			int time = t.time;

			if (tempZero == zeroCnt) {
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (InRange(nx, ny) && !bCheck[nx][ny] && board[nx][ny] != 1) {
					bCheck[nx][ny] = true;
					if (board[nx][ny] == 0) {
						tempZero++;
						if (lastTime < time + 1) {
							lastTime = time + 1;
						}
					}
					q.add(new tuple(nx, ny, time + 1));
				}
			}
		}

		if (zeroCnt == tempZero) {
			if (answer > lastTime) {
				answer = lastTime;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		twoCnt = 0;
		board = new int[N][N];
		v = new ArrayList<Point>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 2) {
					v.add(new Point(i, j));
					twoCnt++;
				}

				if (board[i][j] == 0) {
					zeroCnt++;
				}
			}
		}

		check = new boolean[twoCnt];
		Perm(0, 0);

		if (answer == 2100000000) {
			answer = -1;
		}

		bw.write(answer + "\n");
		bw.close();
		br.close();
	}
}