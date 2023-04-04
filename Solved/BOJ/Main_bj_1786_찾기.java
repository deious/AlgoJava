import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		char[] text = in.readLine().toCharArray();
		char[] pattern = in.readLine().toCharArray();

		int tLength = text.length, pLength = pattern.length;

		int[] pi = new int[pLength];
		for (int i = 1, j = 0; i < pLength; i++) {
			while (j > 0 && pattern[i] != pattern[j])
				j = pi[j - 1];

			if (pattern[i] == pattern[j])
				pi[i] = ++j;
			else
				pi[i] = 0;
		}

		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0, j = 0; i < tLength; ++i) {
			while (j > 0 && text[i] != pattern[j])
				j = pi[j - 1];

			if (text[i] == pattern[j]) {
				if (j == pLength - 1) {
					cnt++;
					list.add(i - j);
					j = pi[j];
				} else {
					j++;
				}
			}
		}

		sb.append(cnt).append("\n");
		if (cnt > 0) {
			for (int j = 0; j < list.size(); j++) {
				sb.append(list.get(j) + 1).append(" ");
			}
		}
		bw.write(sb.toString());
		bw.close();
		in.close();
	}
}
