package ex;

import java.io.*;
import java.util.*;

public class Solution_d3_20230214_한빈이와SpotMart_서울_20반_김한성 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		int N, M;
		
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> bag = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				bag.add(Integer.parseInt(st.nextToken()));
			}
			
			Collections.sort(bag, Collections.reverseOrder());
			int temp = 0;
			for (int j = 0; j < N - 1; j++) {
				if (bag.get(j) >= M) {
					continue;
				}
				for (int k = j + 1; k < N; k++) {
					if (bag.get(j) + bag.get(k) <= M) {
						if (temp < bag.get(j) + bag.get(k)) {
							temp = bag.get(j) + bag.get(k);
						}
					}
				}
			}
			
			if (temp == 0) {
				sb.append("#").append(i).append(" ").append(-1).append("\n");
			} else {
				sb.append("#").append(i).append(" ").append(temp).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
