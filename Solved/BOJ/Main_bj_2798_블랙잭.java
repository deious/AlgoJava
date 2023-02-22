import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, R, temp, ans = 2100000000, minNum;
	static int[] a, b;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void perm(int cnt, int start) {

		if (cnt == 3) {
			temp = 0;
			for (int i = 0; i < 3; i++) {
				 temp += b[i];
			}
			
			if (temp > M) {
				return;
			}
			
			if (Math.abs(temp - M) <= minNum) {
				minNum = Math.abs(temp - M);
				ans = temp;
			}
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			b[cnt] = a[i];
			perm(cnt + 1, i + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		a = new int[N];
		b = new int[3];
		
		st = new StringTokenizer(s = br.readLine());
		
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		minNum = M;
		perm(0,0);
		
		System.out.println(ans);
		br.close();
	}
}
