import java.io.*;
import java.util.*;

public class Solution {

	static int[][] board;
	static int[][] tempBoard;
	static int[] arr;
	static int N, W, H, idx, answer = 2100000000;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static class brick {
		int x, y, num;

		brick(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

	static boolean InRange(int x, int y) {
		return x >= 0 && x < H && y >= 0 && y < W;
	}

	static void Perm(int cnt) {
		if (cnt == N) {
			idx = 0;
			SetBoard();
			CheckBrick();
			BrickCnt();

			return;
		}

		for (int i = 0; i < W; i++) {
			arr[cnt] = i;
			Perm(cnt + 1);
		}
	}

	static void SetBoard() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				tempBoard[i][j] = board[i][j];
			}
		}
	}

	static void CheckBrick() {
		int count = N;
		while (count > 0) {
			int col = arr[idx];
			for (int i = 0; i < H; i++) {
				if (tempBoard[i][col] != 0) {
					ActiveBoom(i, col);
					idx++;
					count--;
					MoveBrick();
					break;
				}
				else if (i == H - 1){
					count--;
				}
			}
		}
	}

	static void ActiveBoom(int x, int y) {
		int num = tempBoard[x][y] - 1;
		ArrayDeque<brick> q = new ArrayDeque<>();
		q.offer(new brick(x, y, num));
		tempBoard[x][y] = 0;

		while (!q.isEmpty()) {
			brick b = q.poll();
			int range = b.num;
			if (range != 0) {
				for (int i = 0; i < 4; i++) {
					int nx = b.x + dx[i];
					int ny = b.y + dy[i];
					int tempRange = range;

					while (InRange(nx, ny) && tempRange > 0) {
						if (tempBoard[nx][ny] != 0) {
							q.offer(new brick(nx, ny, tempBoard[nx][ny] - 1));
							tempBoard[nx][ny] = 0;
							tempRange--;
							nx += dx[i];
							ny += dy[i];

						} else {
							nx += dx[i];
							ny += dy[i];
							tempRange--;
						}
					}
				}
			}
		}
	}
	
	static void MoveBrick() {
		ArrayDeque<brick> q = new ArrayDeque<>();
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if (tempBoard[j][i] != 0) {
					q.offer(new brick(i, j, tempBoard[j][i]));
					tempBoard[j][i] = 0;
				}
			}
			
			for (int j = H - 1; j >= 0; j--) {
				if (q.isEmpty()) {
					break;
				}
				
				if (tempBoard[j][i] == 0) {
					brick b = q.pollLast();
					tempBoard[j][i] = b.num;
				}
			}
		}
	}
	
	static void BrickCnt() {
		int temp = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j <W; j++) {
				if (tempBoard[i][j] != 0) {
					temp++;
				}
			}
		}
		
		if (answer > temp) {
			answer = temp;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			board = new int[H][W];
			tempBoard = new int[H][W];
			arr = new int[N];
			answer = 2100000000;

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Perm(0);
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
