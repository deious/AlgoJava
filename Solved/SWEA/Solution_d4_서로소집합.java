import java.io.*;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[] p;

	static void make() {
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}
	}

	static int find(int a) {
		if (p[a] == a) {
			return a;
		}

		return p[a] = find(p[a]);
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) {
			return;
		}

		p[bRoot] = aRoot;
		return;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		int m, a, b, oper;
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			p = new int[N + 1];
			make();
			sb.append("#").append(i).append(" ");
			
			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				oper = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());

				if (oper == 0) {
					union(a, b);
				} else {
					if (find(a) == find(b)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
