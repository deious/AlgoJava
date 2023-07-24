import java.io.*;
import java.util.*;

class Pair{
	int x, y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Main{

	static int[][] board;
	static int[][] tempBoard;
	static int N, Q, boardSize, rSize, ice, lump;
	
	static boolean[][] boardCheck;

	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };

	static boolean InRange(int x, int y) {
		return x >= 0 && x < boardSize && y >= 0 && y < boardSize;
	}

	static void Separation(int x, int y, int size, boolean check) {
		if (check) {
			Rotate(x, y, size);
			return;
		} else {
			int half = size / 2;
			boolean flag = false;
			if (half == rSize) {
				flag = true;
			}
			Separation(x, y, half, flag);
			Separation(x, y + half, half, flag);
			Separation(x + half, y, half, flag);
			Separation(x + half, y + half, half, flag);
		}
		
		return;
	}

	static void Rotate(int x, int y, int size) {
		int[][] temp = new int[size][size];
		
		for (int i = 0; i <size; i++) {
			System.arraycopy(board[x + i], y, temp[i], 0, size);
		}
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[x + j][y + size - i - 1] = temp[i][j];
			}
		}
		
		return;
	}

	static void Melt() {
		int tempBoard[][] = new int[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (board[i][j] != 0) {
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						if ((InRange(nx, ny) && board[nx][ny] != 0)) {
							cnt++;
						}
					}
					if (cnt < 3) {
						tempBoard[i][j]--;
					}
				}
			}
		}
		
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				board[i][j] += tempBoard[i][j];
			}
		}
	}
	static void BFS(int x, int y) {
		ArrayDeque<Pair> q = new ArrayDeque<Pair>();
		q.offer(new Pair(x, y));
		int temp = 0;
		while(!q.isEmpty()) {
			Pair p = q.poll();
			ice += board[p.x][p.y];
			temp++;
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if (InRange(nx, ny) && !boardCheck[nx][ny] && board[nx][ny] != 0) {
					boardCheck[nx][ny] = true;
					q.offer(new Pair(nx, ny));
				}
			}
		}
		if (temp > lump) {
			lump = temp;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		boardSize = 1 << N;
		board = new int[boardSize][boardSize];
		tempBoard = new int[boardSize][boardSize];

		for (int i = 0; i < boardSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < boardSize; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int num;
		for (int i = 0; i < Q; i++) {
			num = Integer.parseInt(st.nextToken());
			boolean c = false;
			rSize = 1 << num;
			
			if(rSize == 1 << N) {
				rSize = 0;
				c = true;
				Separation(0, 0, 1 << N, c);
			} else if (rSize != 1) {
				Separation(0, 0, 1 << N, c);
			}

			Melt();
		}
		
		boardCheck = new boolean[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (!boardCheck[i][j] && board[i][j] != 0) {
					boardCheck[i][j] = true;
					BFS(i, j);
				}
			}
		}
		sb.append(ice).append("\n").append(lump);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
