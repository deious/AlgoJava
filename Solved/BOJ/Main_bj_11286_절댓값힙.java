import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) ->{
			int n = Integer.compare(Math.abs(o1), Math.abs(o2));
			if (n == 0) {
				return Integer.compare(o1, o2);
			}else {
				return n;
			}
		});

		int N = Integer.parseInt(br.readLine());
		int temp;
		for (int i = 0; i < N; i++) {
			temp = Integer.parseInt(br.readLine());
			if (temp == 0) {
				if (pq.isEmpty()) {
					sb.append("0").append("\n");
				} else {
					sb.append(pq.poll()).append("\n");
				}
			} else {
				pq.add(temp);
			}
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}