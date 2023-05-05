import java.io.*;
import java.util.StringTokenizer;

public class Main{

	private static int interval;
	private static int N, minNum = 2100000000;
	private static int[] a, b;

	static void subs(int cnt, int sour, int bitter) {
		if (cnt == N) {
			interval = Math.abs(sour - bitter);
			if (interval < minNum) {
				minNum = interval;
			}
			return;
		}

		subs(cnt + 1, sour * a[cnt], bitter + b[cnt]);
		subs(cnt + 1, sour, bitter);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		a = new int[N];
		b = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n1, n2;
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());

			a[i] = n1;
			b[i] = n2;
		}
		for (int i = 0; i < N; i++) {
			subs(i + 1, a[i], b[i]);
		}
		
		System.out.println(minNum);
		br.close();
	}
}