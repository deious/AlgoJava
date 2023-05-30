import java.io.*;
import java.util.*;

public class Main {
	static int[] arr, tree;
	
	static int Init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = arr[start];
		}

		int mid = (start + end) / 2;

		return tree[node] = Math.min(Init(start, mid, node * 2), Init(mid + 1, end, node * 2 + 1));
	}
	
	static int Find(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 2100000000;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		return Math.min(Find(start, mid, node * 2, left, right), Find(mid + 1, end, node * 2 + 1, left, right));
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int h = (int)Math.ceil(Math.log(N)/ Math.log(2));
		int treeSize = (1 << (h + 1));
		
		tree = new int[treeSize];

		Init(1, N, 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(Find(1, N, 1, a, b)).append("\n");
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}