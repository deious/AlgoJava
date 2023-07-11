import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] dp, arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int answer = 2100000000;

		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][3];
		arr = new int[N + 1][3];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == j) 
					dp[1][j] = arr[1][i];
				else 
					dp[1][j] = 2100000000;
			}

			for (int j = 2; j <= N; j++) {
				dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + arr[j][0];
				dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + arr[j][1];
				dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + arr[j][2];
			}

			for (int j = 0; j < 3; j++) {
				if (i != j)
					answer = Math.min(answer, dp[N][j]);
			}
		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}