import java.io.*;
import java.util.*;

public class Main {

	static int[][] board;
	static int[][] save;
	static int R, C, T, airX1, airX2, answer = 0;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	static boolean InRange(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}

	static void Diffusion() {
		save = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] != 0) {
					int dCnt = 0;
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];

						if (InRange(nx, ny) && board[nx][ny] != -1) {
							dCnt++;
							save[nx][ny] += board[i][j] / 5;
						}
					}
					board[i][j] -= (board[i][j] / 5 * dCnt);
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				board[i][j] += save[i][j];
			}
		}

		Cycle();
	}

	static void Cycle() {
		int temp = board[airX1][C - 1];
		for (int i = C - 1; i > 1; i--) {
			board[airX1][i] = board[airX1][i - 1];
		}
		board[airX1][1] = 0;

		int temp2 = board[0][C - 1];
		for (int i = 0; i < airX1 - 1; i++) {
			board[i][C - 1] = board[i + 1][C - 1];
		}
		board[airX1 - 1][C - 1] = temp;

		temp = board[0][0];
		for (int i = 0; i < C - 2; i++) {
			board[0][i] = board[0][i + 1];
		}
		board[0][C - 2] = temp2;

		for (int i = airX1 - 1; i > 1; i--) {
			board[i][0] = board[i - 1][0];
		}
		board[1][0] = temp;

		temp = board[airX2][C - 1];
		for (int i = C - 1; i > 1; i--) {
			board[airX2][i] = board[airX2][i - 1];
		}
		board[airX2][1] = 0;

		temp2 = board[R - 1][C - 1];
		for (int i = R - 1; i > airX2 + 1; i--) {
			board[i][C - 1] = board[i - 1][C - 1];
		}
		board[airX2 + 1][C - 1] = temp;

		temp = board[R - 1][0];
		for (int i = 0; i < C - 2; i++) {
			board[R - 1][i] = board[R - 1][i + 1];
		}
		board[R - 1][C - 2] = temp2;

		for (int i = airX2 + 1; i < R - 2; i++) {
			board[i][0] = board[i + 1][0];
		}
		board[R - 2][0] = temp;
	}

	static void CountDust() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				answer += board[i][j];
			}
		}
		answer += 2;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		board = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == -1) {
					airX2 = i;
				}
			}
		}
		airX1 = airX2 - 1;
		for (int i = 0; i < T; i++) {
			Diffusion();
		}
		
		CountDust();

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}