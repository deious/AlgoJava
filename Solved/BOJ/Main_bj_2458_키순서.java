import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static boolean check[][];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		check = new boolean[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			check[i][i] = true;
		}
		
		int a, b;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			check[a][b] = true;
			//check[b][a] = true;
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (check[i][k] && check[k][j]) {
						check[i][j] = true;
					}
				}
			}
		}
		
		int answer = 0;
		boolean flag;
		for (int i = 1; i <= N; i++) {
			flag = false;
			for (int j = 1; j <= N; j++) {
				if (!check[i][j] && !check[j][i]) {
					flag = true;
					break;
				}
			}
			
			if (!flag) {
				answer++;
			}
		}
		
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
