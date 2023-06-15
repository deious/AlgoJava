import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, R;
	static int[] a, b;
	static boolean[] v;
	
	static StringBuilder sb = new StringBuilder();
	
	static void perm(int cnt) {
		if (cnt == R) {
			for (int i = 0; i < R; i++) {
				sb.append(b[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 0; i < N; i++) {
			if (v[i]) continue;
			v[i] = true;
			b[cnt] = a[i];
			perm(cnt+1);
			b[cnt] = 0;
			v[i] = false;
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
		v = new boolean[N];
		
		perm(0);
		
		System.out.println(sb);
	}

}
