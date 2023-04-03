import java.io.*;

public class Main {

	static int board[][];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		String s;
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < N; j++) {
				if (i == j) {
					board[i][j] = 0;
				} else if (s.charAt(j) == 'Y') {
					board[i][j] = 1;
				} else if (s.charAt(j) == 'N') {
					board[i][j] = 210000000;
				}
			}
		}

		int answer = 0;
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					board[i][j] = Math.min(board[i][j], board[i][k] + board[k][j]);
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			int temp = 0;
			for (int j = 0; j < N; j++) {
				if (i == j) {
					continue;
				} else {
					if (board[i][j] <= 2) {
						temp++;
					}
				}
			}
			answer = Math.max(answer, temp);
		}
		
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
