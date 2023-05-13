import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		
		int len1 = s1.length() + 1;
		int len2 = s2.length() + 1;
		int[][] dp = new int[len1][len2];
		
		
		int answer = 0;
		for (int i = 1; i < len1; i++) {
			for (int j = 1; j < len2; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					answer = Math.max(answer, dp[i][j]);
				}
			}
		}
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
