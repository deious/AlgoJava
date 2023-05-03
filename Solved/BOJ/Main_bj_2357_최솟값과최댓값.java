import java.io.*;
import java.util.*;

public class Main {
	static int[] arr, minTree, maxTree;
	
	static int MinInit(int start, int end, int node) {
		if (start == end) {
			return minTree[node] = arr[start];
		}

		int mid = (start + end) / 2;

		return minTree[node] = Math.min(MinInit(start, mid, node * 2), MinInit(mid + 1, end, node * 2 + 1));
	}
	
	static int MaxInit(int start, int end, int node) {
		if (start == end) {
			return maxTree[node] = arr[start];
		}

		int mid = (start + end) / 2;

		return maxTree[node] = Math.max(MaxInit(start, mid, node * 2), MaxInit(mid + 1, end, node * 2 + 1));
	}
	
	static int FindMin(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 2100000000;
		}

		if (left <= start && end <= right) {
			return minTree[node];
		}

		int mid = (start + end) / 2;
		return Math.min(FindMin(start, mid, node * 2, left, right), FindMin(mid + 1, end, node * 2 + 1, left, right));
	}
	
	static int FindMax(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 0;
		}

		if (left <= start && end <= right) {
			return maxTree[node];
		}

		int mid = (start + end) / 2;
		return Math.max(FindMax(start, mid, node * 2, left, right), FindMax(mid + 1, end, node * 2 + 1, left, right));
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
		
		minTree = new int[treeSize];
		maxTree = new int[treeSize];

		MinInit(1, N, 1);
		MaxInit(1, N, 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(FindMin(1, N, 1, a, b)).append(" ");
			sb.append(FindMax(1, N, 1, a, b)).append("\n");
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}