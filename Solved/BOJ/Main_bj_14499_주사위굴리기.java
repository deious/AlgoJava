import java.io.*;
import java.util.*;

public class Main {

	static int[] dice = new int[6];
	static int[][] board;

	static int N, M, K, x, y;

	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };

	static StringBuilder sb = new StringBuilder();

	static boolean InRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	static void Move(int dir) {
		if (InRange(x + dx[dir - 1], y + dy[dir - 1])) {
			if (dir == 1) {
				int temp = dice[5];
				dice[5] = dice[3];
				dice[3] = dice[4];
				dice[4] = dice[1];
				dice[1] = temp;
			} else if (dir == 2) {
				int temp = dice[5];
				dice[5] = dice[1];
				dice[1] = dice[4];
				dice[4] = dice[3];
				dice[3] = temp;
			} else if (dir == 3) {
				int temp = dice[1];
				dice[1] = dice[0];
				dice[0] = dice[3];
				dice[3] = dice[2];
				dice[2] = temp;
			} else {
				int temp = dice[1];
				dice[1] = dice[2];
				dice[2] = dice[3];
				dice[3] = dice[0];
				dice[0] = temp;
			}
			
			x += dx[dir - 1];
			y += dy[dir - 1];
			
			if (board[x][y] == 0) {
				board[x][y] = dice[1];
			} else {
				dice[1] = board[x][y];
				board[x][y] = 0;
			}
			
			sb.append(dice[3]).append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			Move(Integer.parseInt(st.nextToken()));
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}