import java.io.*;
import java.util.*;

public class Main {
	static int[] tree, arr;
	
	static int init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = arr[start];
		}

		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1);
	}

	static int multiple(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 1;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		return multiple(start, mid, node * 2, left, right) * multiple(mid + 1, end, node * 2 + 1, left, right);
	}

	static int update(int start, int end, int node, int idx, int val) {
		if (idx < start || idx > end) {
			return tree[node];
		}

		if (start == end) {
			return tree[node] = val;
		}

		int mid = (start + end) / 2;
		return tree[node] = update(start, mid, node * 2, idx, val) * update(mid + 1, end, node * 2 + 1, idx, val);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		String input = "";
		while ((input = br.readLine()) != null) {
			st = new StringTokenizer(input);

			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			arr = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				int temp = Integer.parseInt(st.nextToken());

				arr[i] = (temp == 0) ? 0 : (temp > 0) ? 1 : -1;
			}
			
			tree = new int[N * 4];
			init(1, N, 1);

			while (K-- > 0) {
				st = new StringTokenizer(br.readLine());

				String command = st.nextToken();
				int i = Integer.parseInt(st.nextToken());

				if (command.equals("C")) {
					int V = Integer.parseInt(st.nextToken());
					V = (V == 0) ? 0 : (V > 0) ? 1 : -1;

					update(1, N, 1, i, V);
				} else if (command.equals("P")) {
					int j = Integer.parseInt(st.nextToken());
					long res = multiple(1, N, 1, i, j);

					sb.append((res == 0) ? 0 : (res > 0) ? "+" : "-");
				}
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}