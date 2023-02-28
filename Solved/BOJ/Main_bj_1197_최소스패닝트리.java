import java.io.*;
import java.util.*;

public class Main {

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
	}

	static int V, E;
	static Edge[] edges;
	static int[] p;

	static void make() {
		p = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			p[i] = i;
		}
	}

	static int find(int a) {
		if (p[a] == a) {
			return a;
		}

		return p[a] = find(p[a]);
	}

	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB) {
			return false;
		}

		p[rootB] = rootA;
		return true;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edges = new Edge[E];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(from, to, weight);
		}

		Arrays.sort(edges);
		make();

		long result = 0, cnt = 0;
		for (Edge edge : edges) {
			if (union(edge.from, edge.to)) {
				result += edge.weight;

				if (++cnt == V - 1) {
					break;
				}
			}
		}
		sb.append(result);

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
