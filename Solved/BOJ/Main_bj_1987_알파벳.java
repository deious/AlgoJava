import java.io.*;
import java.util.*;

public class Main {

	static int R, C, answer = 0;
	static boolean[] alpha;
	static char[][] board;

	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };

	static boolean InRange(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}

	static void DFS(int x, int y, int moveCnt) {
		if (moveCnt > answer) {
			answer = moveCnt;
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			int nm = moveCnt + 1;
			
			if (InRange(nx, ny) && !alpha[board[nx][ny] - 'A']) {
				alpha[board[nx][ny] - 'A'] = true;
				DFS(nx, ny, nm);
				alpha[board[nx][ny] - 'A'] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		alpha = new boolean[27];
		board = new char[R][C];

		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = s.charAt(j);
			}
		}

		alpha[board[0][0] - 'A'] = true;
		DFS(0, 0, 1);
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
