import java.io.*;

public class Main {

	static int[] getPi(String word) {
		int[] pi = new int[word.length()];
		int j = 0;
		for (int i = 1; i < pi.length; i++) {
			while (j > 0 && word.charAt(i) != word.charAt(j)) {
				j = pi[j - 1];
			}

			if (word.charAt(i) == word.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		while (true) {
			String word = br.readLine();
			if (word.equals(".")) {
				break;
			}
			int[] pi = getPi(word);
			int indexCnt = word.length() - pi[word.length() - 1];

			if (word.length() % indexCnt != 0) {
				sb.append("1").append("\n");
			} else {
				sb.append(word.length() / indexCnt).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}