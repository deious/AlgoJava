import java.io.*;
import java.util.*;

public class Main {

	static int N;
	
	static class data implements Comparable<data> {

		int start, end;

		data(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(data o) {

			if (start == o.start)
				return end - o.end;

			return start - o.start;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());

		data d[] = new data[N];
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			d[i] = new data(a, b);
		}
		Arrays.sort(d);

		pq.offer(d[0].end);

		for (int i = 1; i < N; i++) {

			if (d[i].start >= pq.peek())
				pq.poll();

			pq.offer(d[i].end);
		}
		
		sb.append(pq.size());
		bw.write(sb.toString());
		bw.close();
		br.close();

	}
}