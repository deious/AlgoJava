import java.io.*;
import java.util.*;

public class Main {

	static int[] dp = new int[1000001];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		dp[1] = 0;
		
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + 1;
			
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			}
			
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
		}
		
		sb.append(dp[N]);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
