import java.io.*;
import java.util.*;

class SaveData {
	int x1, y1, x2, y2;

	SaveData(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public int getX1() {
		return x1;
	}

	public int getY1() {
		return y1;
	}

	public int getX2() {
		return x2;
	}

	public int getY2() {
		return y2;
	}
}

public class Main {

	static int[] v;
	static int[][] board;
	static int[][] tempBoard;
	static boolean[] check;
	static int N, M, K, startX, startY, endX, endY, answer = 2100000000;
	static ArrayList<SaveData> data = new ArrayList<SaveData>();

	static void Rotate(int[] arr) {
		int index = 0, tempNum = 0;
		int sx1 = 0, sy1 = 0, sx2 = 0, sy2 = 0;
		
		tempBoard = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tempBoard[i][j] = board[i][j];
			}
		}
		
		while (index < K) {
			for (int i = 0; i < K; i++) {
				if (arr[i] == index) {
					SaveData sd = data.get(i);
					sx1 = sd.x1;
					sy1 = sd.y1;
					sx2 = sd.x2;
					sy2 = sd.y2;
				}
			}
			
			index++;
			while (sx1 < sx2 && sy1 < sy2) {
				tempNum = tempBoard[sx1][sy1];
				int tempX1 = sx1, tempY1 = sy1, tempX2 = sx2, tempY2 = sy2;
				while (tempX1 < tempX2) {
					tempBoard[tempX1][tempY1] = tempBoard[tempX1 + 1][tempY1];
					tempX1++;
				}

				while (tempY1 < tempY2) {
					tempBoard[tempX1][tempY1] = tempBoard[tempX1][tempY1 + 1];
					tempY1++;
				}

				while (tempX1 > sx1) {
					tempBoard[tempX1][sy2] = tempBoard[tempX1 - 1][sy2];
					tempX1--;
				}

				while (tempY1 > sy1) {
					tempBoard[tempX1][tempY1] = tempBoard[tempX1][tempY1 - 1];
					tempY1--;
				}
				tempBoard[sx1][sy1 + 1] = tempNum;
				
				sx1++;
				sy1++;
				sx2--;
				sy2--;
			}
		}
		
		int temp;
		for (int i = 0; i < N; i++) {
			temp = 0;
			for (int j = 0; j < M; j++) {
				temp += tempBoard[i][j];
			}
			if (temp < answer) {
				answer = temp;
			}
		}
	}

	static void Perm(int cnt) {
		if (cnt == K) {
			Rotate(v);
			return;
		}

		for (int i = 0; i < K; i++) {
			if (check[i]) {
				continue;
			}

			check[i] = true;
			v[cnt] = i;
			Perm(cnt + 1);
			check[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		v = new int[K];
		check = new boolean[K];
		int r, s, c;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			startX = r - s - 1;
			startY = c - s - 1;
			endX = r + s - 1;
			endY = c + s - 1;

			data.add(new SaveData(startX, startY, endX, endY));
		}

		Perm(0);
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}