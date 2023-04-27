import java.io.*;

public class Main {

	static int getPi(String word) {
		int[] pi = new int[word.length()];
		int j = 0, maxNum = 0;
		for (int i = 1; i < pi.length; i++) {
			while (j > 0 && word.charAt(i) != word.charAt(j)) {
				j = pi[j - 1];
			}

			if (word.charAt(i) == word.charAt(j)) {
				pi[i] = ++j;
				maxNum = Math.max(maxNum, pi[i]);
			}
		}
		return pi[pi.length - 1];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		String s = br.readLine();
		
		sb.append(n - getPi(s));
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}