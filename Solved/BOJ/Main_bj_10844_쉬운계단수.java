import java.io.*;

public class Main {

	static long dp[][];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		dp = new long[N + 1][10];

		for (int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i < N + 1; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0) {
					dp[i][j] += (dp[i - 1][1] % 1000000000);
				} else if (j == 9) {
					dp[i][j] += (dp[i - 1][8] % 1000000000);
				} else {
					dp[i][j] += ((dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000);
				}
			}
		}
		
		long answer = 0;
		for (int i = 1; i < 10; i++) {
			answer += dp[N][i];
		}
		
		answer %= 1000000000;
		
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}