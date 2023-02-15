package ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_d3_20230215_원재의메모리복구하기_서울_20반_김한성 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(in.readLine());
		for (int i = 1; i <= TC; i++) {
			String st = in.readLine();
			int len = st.toString().length();
			char temp = st.toString().charAt(0);
			int totalCnt = 1;
			
			System.out.println(len);
			
			if (temp == '0') {
				totalCnt--;
			}
			
			for (int j = 1; j < len; j++) {
				if (temp != st.toString().charAt(j)) {
					totalCnt++;
					temp = st.toString().charAt(j);
				}
			}

			
			System.out.println("#" + i + " " + totalCnt);
		}
		in.close();
	}
}
