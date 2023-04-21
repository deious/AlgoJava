import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static int dp[], arr[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		arr = new int[n];
		dp = new int[k + 1];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i <= k; i++) {
			dp[i] = 2100000000;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = arr[i]; j <= k; j++) {
				dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
			}
		}
		
		
		if (dp[k] == 2100000000) {
			sb.append(-1);
		} else {
			sb.append(dp[k]);
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}