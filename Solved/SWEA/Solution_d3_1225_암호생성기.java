package ex;

import java.io.*;
import java.util.*;

public class Solution_d3_20230210_암호생성기_서울_20반_김한성 {

	public static void main(String[] args) throws IOException {
		ArrayDeque<Integer> q = new ArrayDeque<>(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s;
		StringTokenizer st;
		int temp = 0;
		int minus = 0;
		
		for (int i = 1; i <= 10; i++) {
			br.readLine();
			s = br.readLine();
			st = new StringTokenizer(s);
			
			for (int j = 0; j < 8; j++) {
				q.add(Integer.parseInt(st.nextToken()));
			}
			
			minus = 1;
			while (true) {
				temp = q.poll();
				temp -= minus++;

				if (temp <= 0) {
					q.add(0);
					break;
				}else {
					q.add(temp);
				}
				
				if (minus > 5) {
					minus = 1;
				}
			}
			
			sb.append("#" + i + " ");
			while(true) {
				if (q.isEmpty()) {
					break;
				}
				sb.append(q.poll() + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
