import java.io.*;
import java.util.*;

public class Main {

	static int[][] board;
	static int[][] tempBoard;
	static double avg;
	static int N, M, T, answer = 0;

	static ArrayDeque<pair> q;

	static class pair {
		int x, y;

		pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void Rotate(int num, int dir, int cnt) {
		tempBoard = new int[N + 1][M];
		for (int i = 0; i < N + 1; i++) {
			tempBoard[i] = board[i].clone();
		}
		if (dir == 0) {
			for (int i = num; i <= N; i += num) {
				for (int j = 0; j < M; j++) {
					tempBoard[i][(j + cnt) % M] = board[i][j];
				}
			}
		} else {
			for (int i = num; i <= N; i += num) {
				for (int j = 0; j < M; j++) {
					int idx = 0;
					if (j - cnt >= 0) {
						idx = j - cnt;
					} else {
						idx = M + j - cnt;
					}
					tempBoard[i][idx] = board[i][j];
				}
			}
		}

		for (int i = 1; i < N + 1; i++) {
			board[i] = tempBoard[i].clone();
		}

		totalCheck();
	}

	static void totalCheck() {
		q = new ArrayDeque<>();
		boolean check = false;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M - 1; j++) {
				if (board[i][j] != 0) {
					if (board[i][j] == board[i][j + 1]) {
						q.offer(new pair(i, j));
						q.offer(new pair(i, j + 1));
						check = true;
					}
				}
			}

			if (board[i][0] !=0 && (board[i][M - 1] == board[i][0])) {
				q.offer(new pair(i, M - 1));
				q.offer(new pair(i, 0));
				check = true;
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 1; j < N; j++) {
				if (board[j][i] != 0) {
					if (board[j][i] == board[j + 1][i]) {
						q.offer(new pair(j, i));
						q.offer(new pair(j + 1, i));
						check = true;
					}
				}
			}
		}

		Change(check);
	}

	static void Change(boolean check) {
		int rCnt = 0;
		if (!check) {
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					avg += board[i][j];
					if (board[i][j] != 0) {
						rCnt++;
					}
				}
			}
			avg /= (rCnt * 1.0);

			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[i][j] != 0) {
						if (board[i][j] > avg) {
							board[i][j]--;
						} else if (board[i][j] < avg) {
							board[i][j]++;
						}
					}
				}
			}
		} else {
			while (!q.isEmpty()) {
				pair p = q.poll();
				board[p.x][p.y] = 0;
			}
		}
	}

	static void SumBoard() {
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				answer += board[i][j];
			}
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
		T = Integer.parseInt(st.nextToken());

		board = new int[N + 1][M];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int num = 0;
		int dir = 0;
		int cnt = 0;
		for (int i = 0; i < T; i++) {
			avg = 0;
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			cnt = Integer.parseInt(st.nextToken());

			Rotate(num, dir, cnt);
		}

		SumBoard();
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}