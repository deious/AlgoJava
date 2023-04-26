import java.io.*;
import java.util.*;

public class Main {
	static long[] arr, tree;
	
	static long Init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = arr[start];
		}

		int mid = (start + end) / 2;

		return tree[node] = Init(start, mid, node * 2) + Init(mid + 1, end, node * 2 + 1);
	}

	static long Update(int start, int end, int node, int idx, long dif) {
		if (idx < start || idx > end) {
			return tree[node];
		}

		if (start == end) {
			return tree[node] = dif;
		}

		int mid = (start + end) / 2;
		return tree[node] = Update(start, mid, node * 2, idx, dif) + Update(mid + 1, end, node * 2 + 1, idx, dif);
	}
	
	static long Sum(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 0;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		return Sum(start, mid, node * 2, left, right) + Sum(mid + 1, end, node * 2 + 1, left, right);
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		arr = new long[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		int h = (int)Math.ceil(Math.log(N)/ Math.log(2));
		int treeSize = (1 << (h + 1));
		
		tree = new long[treeSize];

		Init(1, N, 1);

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (x > y) {
				sb.append(Sum(1, N, 1, y, x)).append("\n");
			} else {
				sb.append(Sum(1, N, 1, x, y)).append("\n");
			}
			Update(1, N, 1, a, b);
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}