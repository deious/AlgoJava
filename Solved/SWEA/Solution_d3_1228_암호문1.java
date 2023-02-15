package ex;

import java.io.*;
import java.util.*;

public class Solution_d3_20230213_암호문1_서울_20반_김한성 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int len, num, start, end;
		
		for (int i = 1; i <= 10; i++) {
			ArrayList<String> password = new ArrayList<String>();
			st = new StringTokenizer(br.readLine());
			len = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < len; j++) {
				password.add(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < num; j++) {
				st.nextToken();
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				
				for (int k = 0; k < end; k++) {
					password.add(start++, st.nextToken());
				}
			}
			
			sb.append("#").append(i).append(" ");
			for (int j = 0; j < 10; j++) {
				sb.append(password.get(j).toString()).append(" ");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
