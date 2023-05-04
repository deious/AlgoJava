import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static int dp[][][], order[], len;
	static int MoveCheck(int orderIdx, int open1, int open2) {
		if (orderIdx > len)
			return 0;

		int result = dp[orderIdx][open1][open2];

		if (result != -1)
			return result;

		result = Math.min(Math.abs(order[orderIdx] - open1) + MoveCheck(orderIdx + 1, order[orderIdx], open2), Math.abs(order[orderIdx] - open2) + MoveCheck(orderIdx + 1, open1, order[orderIdx]));

		return result;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int open1 = Integer.parseInt(st.nextToken());
		int open2 = Integer.parseInt(st.nextToken());
		len = Integer.parseInt(br.readLine());
		
		dp = new int[len + 1][N + 1][N + 1];
		order = new int[len + 1];
		
		for (int i = 1; i <= len; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		
		for (int i = 1; i <= len; i++) {
			order[i] = Integer.parseInt(br.readLine());
		}

		sb.append(MoveCheck(1, open1, open2));
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}