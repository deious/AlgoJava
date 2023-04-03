import java.io.*;
import java.util.*;

public class Main {

	static int board[][];
	static boolean check[][];
	static ArrayList<pair> arr;
	static int sx, sy, ex, ey;

	static class pair {
		int x, y;

		pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int Distance(pair p1, pair p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			arr = new ArrayList<pair>();
			int n = Integer.parseInt(br.readLine());
			check = new boolean[n + 2][n + 2];
			
			for (int j = 0; j < n + 2; j++) {
				st = new StringTokenizer(br.readLine());
				arr.add(new pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			for (int j = 0; j < n + 2; j++) {
				for (int k = 0; k < n + 2; k++) {
					if (Distance(arr.get(j), arr.get(k)) <= 1000) {
						check[j][k] = check[k][j] = true;
					}
				}
			}
			
			for (int l = 0; l < n + 2; l++) {
				for (int j = 0; j < n + 2; j++) {
					for (int k = 0; k < n + 2; k++) {
						if (check[j][l] && check[l][k]) {
							check[j][k] = true;
						}
					}
				}
			}
			
			sb.append(check[0][n + 1] ? "happy" : "sad").append("\n");
		}

		bw.write(sb.toString());
		bw.close();
		br.close();

	}

}
