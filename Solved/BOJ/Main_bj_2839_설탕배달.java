import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] dp = new int[5001];
	
	static int DP(int n) {
		if (n < 0) {
			return 5000;
		}
		
		if (n == 3 || n == 5) {
			return 1;
		}
		
		if (dp[n] != 0) {
			return dp[n];
		}
		return dp[n] = Math.min(DP(n - 3) + 1, DP(n - 5) + 1);
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		
		dp[3] = 1;
		dp[5] = 1;
		
		dp[N] = DP(N);
		if (dp[N] >= 5000) {
			dp[N] = -1;
		}
		
		bw.write(sb.append(dp[N]).toString());
		bw.close();
		br.close();
	}

}
