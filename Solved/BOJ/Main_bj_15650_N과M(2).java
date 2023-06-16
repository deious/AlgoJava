import java.io.*;
import java.util.*;

public class Main{

	static int N, R;
	static int[] a, b;
	
	static StringBuilder sb = new StringBuilder();
	
	private static void perm(int cnt, int start) {

		if (cnt == R) {
			for (int i = 0; i < R; i++) {
				sb.append(b[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= N; i++) {
			b[cnt] = i;
			perm(cnt + 1, i + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		N = Integer.parseInt(st.nextToken());
		a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = i + 1;
		}
		R = Integer.parseInt(st.nextToken());
		b = new int[R];
		
		perm(0, 1);
		
		System.out.println(sb);
		br.close();
	}

}