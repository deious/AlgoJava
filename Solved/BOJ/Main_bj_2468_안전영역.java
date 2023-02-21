import java.io.*;
import java.util.*;

public class Main {

	static int N, ground = 0, temp = 0;
	static int[][] board;
	static boolean[][] check;

	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };

	static boolean InRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static void DFS(int a, int b) {
		for (int i = 0; i < 4; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];

			if (InRange(nx, ny) && !check[nx][ny] && board[nx][ny] != 0) {
				check[nx][ny] = true;
				DFS(nx, ny);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());

		board = new int[N][N];
		ground = 1;

		int maxHeight = 0;
		for (int j = 0; j < N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < N; k++) {
				board[j][k] = Integer.parseInt(st.nextToken());
				if (board[j][k] > maxHeight) {
					maxHeight = board[j][k];
				}
			}
		}

		for (int j = 1; j < maxHeight; j++) {
			check = new boolean[N][N];
			temp = 0;
			for (int k = 0; k < N; k++) {
				for (int l = 0; l < N; l++) {
					if (board[k][l] == j) {
						board[k][l] = 0;
					}
				}
			}

			for (int k = 0; k < N; k++) {
				for (int l = 0; l < N; l++) {
					if (!check[k][l] && board[k][l] != 0) {
						check[k][l] = true;
						DFS(k, l);
						temp++;
					}
				}
			}

			if (temp > ground) {
				ground = temp;
			}
		}
		sb.append(ground + "\n");

		System.out.println(sb);
		br.close();
	}

}
