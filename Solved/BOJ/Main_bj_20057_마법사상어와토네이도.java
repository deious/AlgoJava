import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] board;

	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { -1, 0, 1, 0 };

	static int ddx[][] = {{ -2, -1, -1, -1, 0, 0, 1, 1, 1, 2 },{0, 1, 0, -1, 2, 1, 1, 0, -1, 0},{-2, -1, -1, -1, 0, 0, 1, 1, 1, 2},{0, -1, 0, 1, -2, -1, -1, 0, 1, 0}};
	static int ddy[][] = {{ 0, -1, 0, 1, -2, -1, -1, 0, 1, 0 },{-2, -1, -1, -1, 0, 0, 1, 1, 1, 2 },{0, 1, 0, -1, 2, 1, 1, 0, -1, 0},{2, 1, 1, 1, 0, 0,-1, -1, -1, -2}};
	static double percent[] = { 0.02, 0.1, 0.07, 0.01, 0.05, 0, 0.1, 0.07, 0.01, 0.02 };

	static boolean InRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int startX = N / 2;
		int startY = N / 2;
		int cnt = N * N - 1;
		int moveMax = 1;
		int moveMaxCnt = 0;
		int moveCnt = 0;
		int len = 2;
		int dir = 0;
		int answer = 0;

		for (int i = 0; i < cnt; i++) {

			startX += dx[dir];
			startY += dy[dir];
			int temp = 0;

			for (int j = 0; j < 10; j++) {
				int nx = startX + ddx[dir][j];
				int ny = startY + ddy[dir][j];

				if (j == 5) {
					continue;
				}

				if (InRange(nx, ny)) {
					board[nx][ny] += (int) (board[startX][startY] * percent[j]);
					temp += (int) (board[startX][startY] * percent[j]);
				} else {
					answer += (int) (board[startX][startY] * percent[j]);
					temp += (int) (board[startX][startY] * percent[j]);
				}
			}

			if (InRange(startX + dx[dir], startY + dy[dir])) {
				board[startX + dx[dir]][startY + dy[dir]] += (board[startX][startY] - temp);
			} else {
				answer += (board[startX][startY] - temp);
			}
			
			board[startX][startY] = 0;

			if (++moveMaxCnt == moveMax) {
				dir = (dir + 1) % 4;
				moveMaxCnt = 0;
			}

			if (++moveCnt == len) {
				moveMax++;
				moveCnt = 0;
				len += 2;
			}

		}
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}