import java.io.*;
import java.util.*;

public class Solution {

	static int[] arr;
	static boolean[] group;
	static int N, M;

	static int find(int num) {
		if (arr[num] == num) {
			return num;
		}

		return arr[num] = find(arr[num]);
	}

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA != rootB) {
			if (rootA < rootB) {
				arr[rootB] = rootA;
			} else {
				arr[rootA] = rootB;
			}
			return;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int a, b, answer = 0;

		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			arr = new int[N + 1];
			group = new boolean[N + 1];
			answer = 0;

			for (int j = 1; j <= N; j++) {
				arr[j] = j;
			}

			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
			
			for (int j = 1; j <= N; j++) {
				find(j);
				group[arr[j]] = true;
			}


			for (int j = 1; j <= N; j++) {
				if (group[j]) {
					answer++;
				}
			}
			sb.append("#").append(i).append(" ").append(answer).append("\n");
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
