import java.io.*;
import java.util.*;

public class Main {
	static int N, answer = 0;
	static int[] player, ru;
	static int[][] hit;
	static boolean[] check;

	static void Comb(int start, int cnt) {
		if (cnt == 9) {
			Play();
			return;
		}

		if (cnt == 3) {
			player[cnt] = 0;
			Comb(start + 1, cnt + 1);
			return;
		}

		for (int i = 1; i < 9; i++) {
			if (check[i]) {
				continue;
			}

			check[i] = true;
			player[cnt] = i;
			Comb(i + 1, cnt + 1);
			check[i] = false;
		}
	}

	static void Play() {
		int score = 0;
		int outCnt = 0;
		int idx = 0;

		for (int i = 0; i < N; i++) {
			outCnt = 0;
			ru = new int[4];
			while (outCnt < 3) {
				if (hit[i][player[idx]] == 1) {
					ru[0] = ru[3];
					ru[3] = ru[2];
					ru[2] = ru[1];
					ru[1] = 1;
				} else if (hit[i][player[idx]] == 2) {
					ru[0] = ru[3] + ru[2];
					ru[3] = ru[1];
					ru[2] = 1;
					ru[1] = 0;
				}else if (hit[i][player[idx]] == 3) {
					ru[0] = ru[3] + ru[2] + ru[1];
					ru[3] = 1;
					ru[2] = ru[1] = 0;
				}else if (hit[i][player[idx]] == 4) {
					ru[0] = ru[3] + ru[2] + ru[1] + 1;
					ru[3] = ru[2] = ru[1] = 0;
				} else {
					outCnt++;
				}
				
				idx++;
				
				if (ru[0] > 0) {
					score += ru[0];
					ru[0] = 0;
				}
				
				if (idx > 8) {
					idx = 0;
				}
			}
		}

		if (score > answer) {
			answer = score;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		hit = new int[N][9];
		player = new int[9];
		check = new boolean[9];
		check[0] = true;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				hit[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Comb(0, 0);
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}