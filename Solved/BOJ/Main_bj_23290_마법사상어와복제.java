import java.io.*;
import java.util.*;

public class Main {
	static int M, S;
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };

	static int[] edx = { -1, 0, 1, 0 };
	static int[] edy = { 0, -1, 0, 1 };

	static int[][][] board = new int[4][4][8];
	static int[][][] temp;
	static int[][] smell = new int[4][4];
	static int[][] fishBoard;
	static int[] eaten;
	
	static int Max;
	static int totalFish;
	
	static Shark shark;
	
	static class Shark {
		int x, y;

		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static boolean InRange(int x, int y) {
		return x >= 0 && x < 4 && y >= 0 && y < 4;
	}

	static void FindEatDir() {
		int x = shark.x;
		int y = shark.y;
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			int nx1 = x + edx[i];
			int ny1 = y + edy[i];
			if (!InRange(nx1, ny1)) {
				continue;
			}

			sum += fishBoard[nx1][ny1];

			for (int j = 0; j < 4; j++) {
				int nx2 = nx1 + edx[j];
				int ny2 = ny1 + edy[j];
				if (!InRange(nx2, ny2)) {
					continue;
				}
				sum += fishBoard[nx2][ny2];
				for (int k = 0; k < 4; k++) {
					int nx3 = nx2 + edx[k];
					int ny3 = ny2 + edy[k];
					if (!InRange(nx3, ny3)) {
						continue;
					}

					if (nx1 != nx3 || ny1 != ny3) {
						sum += fishBoard[nx3][ny3];
					}

					if (Max < sum) {
						Max = sum;
						eaten[0] = i;
						eaten[1] = j;
						eaten[2] = k;
					}

					if (nx1 != nx3 || ny1 != ny3) {
						sum -= fishBoard[nx3][ny3];
					}

				}
				sum -= fishBoard[nx2][ny2];
			}

			sum -= fishBoard[nx1][ny1];
		}

	}

	static void Delete() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (smell[i][j] != 0) {
					smell[i][j]--;
				}
			}
		}
		int nx = shark.x;
		int ny = shark.y;
		for (int i = 0; i < 3; i++) {
			int d = eaten[i];

			nx += edx[d];
			ny += edy[d];

			if (fishBoard[nx][ny] != 0) {
				temp[nx][ny] = new int[8];
				fishBoard[nx][ny] = 0;
				smell[nx][ny] = 2;
			}
		}
		shark = new Shark(nx, ny);
	}

	static void MoveFish() {
		fishBoard = new int[4][4];
		temp = new int[4][4][8];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int d = 0; d < 8; d++) {
					if (board[i][j][d] == 0) {
						continue;
					} else {
						int tempd = d;

						while (true) {
							int nx = i + dx[tempd];
							int ny = j + dy[tempd];

							if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || smell[nx][ny] != 0
									|| (shark.x == nx && shark.y == ny)) {
								tempd--;
								if (tempd == -1) {
									tempd = 7;
								}
								if (tempd == d) {
									temp[i][j][d] += board[i][j][d];
									fishBoard[i][j] += board[i][j][d];
									break;
								}
								continue;
							}

							temp[nx][ny][tempd] += board[i][j][d];
							fishBoard[nx][ny] += board[i][j][d];
							break;
						}
					}
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		totalFish = M;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			board[x][y][d]++;
		}

		st = new StringTokenizer(br.readLine());
		shark = new Shark(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

		for (int s = 0; s < S; s++) {
			MoveFish();

			eaten = new int[3];
			Max = -1;

			FindEatDir();
			Delete();

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (fishBoard[i][j] != 0) {
						for (int d = 0; d < 8; d++) {
							board[i][j][d] += temp[i][j][d];
						}
					}
				}
			}
			totalFish = totalFish * 2 - Max;

		}
		sb.append(totalFish);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}