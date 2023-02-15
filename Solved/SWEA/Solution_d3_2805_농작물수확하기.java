package ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_d3_20230207_농작물수확하기_서울_20반_김한성 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;

		int t = Integer.parseInt(br.readLine());
		char[][] charArr;
		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			int sum = 0;
			
			charArr = new char[n][];
			for (int i = 0; i < n; i++) {
				s = br.readLine();
				charArr[i] = s.toCharArray();
			}
			
			int index = 0;
			for (int i = 0; i < n; i++) {
				if ((n / 2 - i) >= 0) {
					for (int j = n / 2 - i; j <= n / 2 + i; j++) {
						sum += (charArr[i][j] - '0');
					}
				} else {
					for (int j = n / i + index; j < (n - n / i) - index; j++) {
						sum += (charArr[i][j] - '0');
						index++;
					}
					
					if (i == n - 1) {
						sum += (charArr[i][n / 2] - '0');
					}
				}
			}
			System.out.println("#" + tc + " " + sum);
		}
	}

}
