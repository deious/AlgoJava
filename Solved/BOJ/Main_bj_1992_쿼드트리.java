import java.io.*;
import java.util.*;

public class Main {

	static int[][] board;
	static StringBuilder sb = new StringBuilder();

	static void cut(int r, int c, int size) {
		
		int sum = 0;
		for (int i = r, rEnd = r + size; i < rEnd; i++) {
			for (int j = c, cEnd = c + size; j < cEnd; j++) {
				sum += board[i][j];
			}
		}
		
		if (sum == size * size) {
			sb.append("1");
		}else if(sum == 0) {
			sb.append("0");
		}else {
			sb.append("(");
			int half = size / 2;
			cut(r, c, half);
			cut(r, c + half, half);
			cut(r + half, c, half);
			cut(r + half, c + half, half);
			sb.append(")");
		}

		return;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s;
		int n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		for (int i = 0; i < n; i++) {
			s = br.readLine();
			for (int j = 0; j < n; j++) {
				board[i][j] = s.charAt(j) - '0';
			}
		}
		cut(0,0,n);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}
