package ex;

import java.io.*;
import java.util.*;

public class Solution_d4_20230214_사칙연산유효성검사_서울_20반_김한성 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 10; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			boolean check = false;
			
			if (N % 2 == 0) {
				sb.append("#").append(i).append(" ").append(0).append("\n");
				for (int j = 0; j < N; j++) {
					br.readLine();
				}
				continue;
			}
			
			ArrayList<Character> tree = new ArrayList<Character>();
			tree.add('0');
			for (int j = 1; j <= N; j++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				tree.add(st.nextToken().charAt(0));
			}
			
			for (int j = N; j >= 2; j -= 2) {
				if (Character.isDigit(tree.get(j)) && Character.isDigit(tree.get(j - 1))) {
					char c = tree.get(j/2);
					if (c == '+' || c == '-' || c == '*' || c == '/' ) {
						tree.set(j/2, '0');
					}
				}
				else {
					check = true;
					break;
				}
			}
			if (check) {
				sb.append("#").append(i).append(" ").append(0).append("\n");
			} else {
				sb.append("#").append(i).append(" ").append(1).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
