import java.io.*;
import java.util.*;

public class Main {

	static int[] hat = new int[9];
	static int[] real = new int[7];
	static int[] test = new int[7];

	static void FindNan(int cnt, int start, int sumHat, int[] t) {
		if (cnt == 7) {
			if (sumHat == 100) {
				real = test.clone();
			}
			return;
		}

		for (int i = start; i < 9; i++) {
			t[cnt] = hat[i];
			FindNan(cnt + 1, i + 1, sumHat + hat[i], t);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int num;
		for (int i = 0; i < 9; i++) {
			hat[i] = Integer.parseInt(br.readLine());
		}

		FindNan(0, 0, 0, test);
		Arrays.sort(real);
		for (int i = 0; i < 7; i++) {
			sb.append(real[i]).append("\n");
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
