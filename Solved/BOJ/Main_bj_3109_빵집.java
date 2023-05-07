import java.io.*;
import java.util.*;

public class Main {

	static char[][] board;
	static boolean[][] check;
	static int R, C, answer;
	static boolean endCheck;

	static int dx[] = { -1, 0, 1 };
	static int dy[] = { 1, 1, 1 };

	static boolean InRange(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}
	
	static void DFS(int x, int y) {
		
		if (y == C - 1) {
			endCheck = true;
			answer++;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (InRange(nx, ny) && !check[nx][ny] && board[nx][ny] == '.') {
				if (endCheck) {
					return;
				}
				
				check[nx][ny] = true;
				DFS(nx, ny);
			}
		}
		
		return;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char[R][C];
		check = new boolean[R][C];
		String s;

		for (int i = 0; i < R; i++) {
			s = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		
		for (int i = 0; i < R; i++) {
			check[i][0] = true;
			endCheck = false;
			DFS(i, 0);
		}
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}