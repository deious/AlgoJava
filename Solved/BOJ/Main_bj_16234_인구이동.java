import java.io.*;
import java.util.*;

class data{
	int x, y;
	
	data(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}

public class Main {

	public static int N, L, R, total, dfsCnt, day;
	public static boolean endCheck = false;

	public static int[][] board;
	public static boolean[][] check;

	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };
	
	static Queue<data> q = new LinkedList<data>();

	public static boolean InRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	public static boolean InRangeLR(int x, int y, int nx, int ny) {
		return Math.abs(board[x][y] - board[nx][ny]) >= L && Math.abs(board[x][y] - board[nx][ny]) <= R;
	}

	public static void DFS(int x, int y) {
		int nx, ny;
		total += board[x][y];
		dfsCnt++;
		q.add(new data(x, y));
		for (int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];

			if (InRange(nx, ny) && !check[nx][ny] && InRangeLR(x, y, nx, ny)) {
				check[nx][ny] = true;
				DFS(nx, ny);
			}
		}
	}
	
	public static void SumDFS() {
		if (q.size() == 1) {
			q.poll();
			dfsCnt = 0;
			return;
		}
		
		total /= q.size();
		while (!q.isEmpty()) {
			data d = q.poll();
			board[d.x][d.y] = total;
		}
		
		endCheck = false;
		
		return;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (!endCheck) {
			check = new boolean[N][N];
			endCheck = true;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!check[i][j]) {
						check[i][j] = true;
						total = 0;
						dfsCnt = 0;
						DFS(i, j);
						SumDFS();
					}
				}
			}
			
			if (!endCheck) {
				day++;
			}
		}
		
		System.out.println(day);
        br.close();
	}

}
