import java.io.*;
import java.util.*;

public class Main {
	static int N, x, y, r, c, num = 0;
	static StringBuilder sb = new StringBuilder();

	static void cut(int x, int y, int size) {

		if (x == r && y == c) {
			sb.append(num);
			return;
		}

		if (x <= r && x + size > r && y <= c && y + size > c) {
			int half = size / 2;
			cut(x, y, half);
			cut(x, y + half, half);
			cut(x + half, y, half);
			cut(x + half, y + half, half);
		} else {
			num += size * size;
		}

		return;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		cut(0, 0, 1 << N);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}
