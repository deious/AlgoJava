import java.io.*;
import java.util.*;

public class Main {

	public static long pow(long a, long b) {
		if (b == 0)
			return 1;
		long ans = pow(a, b / 2);
		long next = (ans * ans) % 1000000007;
		if (b % 2 == 0)
			return next;
		else
			return (next * a) % 1000000007;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		long[] factorial = new long[4000001];
		factorial[0] = 1;
		for (int i = 1; i <= 4000000; i++) {
			factorial[i] = (i * factorial[i - 1]) % 1000000007;
		}

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		long ans = (factorial[N] * pow((factorial[R] * factorial[N - R]) % 1000000007, 1000000007 - 2)) % 1000000007;
		sb.append(ans).append("\n");

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
