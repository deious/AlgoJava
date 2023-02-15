package ex;

import java.io.*;
import java.util.*;

public class Solution_d4_20230210_괄호짝짓기_서울_20반_김한성 {

	static Stack<Character> s;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str;
		
		for (int i = 1; i <= 10; i++) {
			N = Integer.parseInt(br.readLine());
			str = br.readLine();
			s = new Stack<>();
			for (int j = 0; j < N; j++) {
				if (s.empty()) {
					s.push(str.charAt(j));
				}else {
					if (s.peek() == '(') {
						if (str.charAt(j) == ')'){
							s.pop();
						}else {
							s.push(str.charAt(j));
						}
					}else if (s.peek() == '[') {
						if (str.charAt(j) == ']'){
							s.pop();
						}else {
							s.push(str.charAt(j));
						}
					}else if (s.peek() == '{') {
						if (str.charAt(j) == '}'){
							s.pop();
						}else {
							s.push(str.charAt(j));
						}
					}else if (s.peek() == '<') {
						if (str.charAt(j) == '>'){
							s.pop();
						}else {
							s.push(str.charAt(j));
						}
					}
				}
			}
			
			sb.append("#" + i + " ");
			if (s.empty()) {
				sb.append(1 + "\n");
			}else {
				sb.append(0 + "\n");
			}
		}
		System.out.println(sb);
		br.close();
	}

}
