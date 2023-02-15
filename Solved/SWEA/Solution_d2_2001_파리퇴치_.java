package ex;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_d2_파리퇴치_서울_20반_김한성 {

	static int[][] board, dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		String s = "";
		StringTokenizer st;
		int N, M;
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(s = br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			board = new int[N + 1][N + 1]; 
			dp = new int[N + 1][N + 1];
			
			for (int j = 1; j <= N; j++) {
				st = new StringTokenizer(s = br.readLine());
				for (int k = 1; k <= N; k++) {
					board[j][k] = Integer.parseInt(st.nextToken());
					dp[j][k] = dp[j - 1][k] + dp[j][k - 1] - dp[j - 1][k - 1] + board[j][k];
				}
			}
			
			int maxNum = 0;
			for (int j = M; j <= N; j++) {
				for (int k = M; k <= N; k++) {
					maxNum = Math.max(maxNum, (dp[j][k] - dp[j - M][k] - dp[j][k - M] + dp[j - M][k - M]));
				}
			}

			sb.append("#" + i + " " + maxNum + "\n");
		}
		
		System.out.println(sb);
		br.close();
	}

}
