import java.io.*;
import java.util.*;

public class Main {
	
	static int N, dfsCnt = 0, maxCnt = 0;
	static int[][] board;
	static boolean[][] check;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static boolean InRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	static void DFS(int x, int y) {
		
		maxCnt++;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (InRange(nx, ny) && !check[nx][ny] && board[nx][ny] == 1) {
				check[nx][ny] = true;
				DFS(nx, ny);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		check = new boolean[N][N];
		String s;
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = s.charAt(j) - '0';
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1 && !check[i][j]) {
					check[i][j] = true;
					maxCnt = 0;
					dfsCnt++;
					DFS(i, j);
					pq.add(maxCnt);
				}
			}
		}
		
		sb.append(dfsCnt).append("\n");
		for (int i = 0; i < dfsCnt; i++) {
			sb.append(pq.poll()).append("\n");
		}
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}
