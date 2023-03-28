import java.io.*;
import java.util.*;

public class Main {

	static int[] dp = new int[1001];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		dp[1] = 1;
		dp[2] = 3;
		
		for (int i = 3; i <= N; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10007;
		}
		
		sb.append(dp[N]);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
