import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] board = new int[101][101];
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int a, b, answer = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			for (int j = a; j < a + 10; j++) {
				for (int k = b; k < b + 10; k++) {
					board[j][k] = 1;
				}
			}
		}
		
		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 100; j++) {
				if (board[i][j] == 1) {
					answer++;
				}
			}
		}
		
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
