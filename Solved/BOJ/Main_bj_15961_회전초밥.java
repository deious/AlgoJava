import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static int sushi[];
	static int check[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		sushi = new int[N];
		check = new int[d + 1];

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			sushi[i] = num;
		}

		int s = 0, e = 0, maxCnt = 0, temp = 0, answer = 0;
		while (s < N - 1) {
			if (temp != k) {
				if (check[sushi[e]] == 0) {
					maxCnt++;
				}

				check[sushi[e]]++;
				e++;
				temp++;

				if (temp == k) {
					if (check[c] == 0) {
						maxCnt++;
					}

					check[c]++;
				}
			} else {
				if (check[sushi[s]] == 1) {
					maxCnt--;
				}
				check[sushi[s]]--;
				s++;

				if (check[sushi[e]] == 0) {
					maxCnt++;
				}
				
				check[sushi[e]]++;
				
				e++;
				if (e >= N) {
					e = 0;
				}
			}

			if (answer < maxCnt) {
				answer = maxCnt;
			}
		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
