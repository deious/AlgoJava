package ex;

import java.io.*;

public class Solution_d2_20230208_달팽이숫자_서울_20반_김한성 {

	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			int N = Integer.parseInt(br.readLine());
			int[][] board = new int[N][N];
		
			int maxNum = N * N, num = 1;
			int x = 0, y = 0;
			int caseNum = 0;
			while(num <= maxNum) {
				if (x < 0 || x >= N || y < 0 || y >= N) {
					x -= dx[caseNum];
					y -= dy[caseNum];
					caseNum = (caseNum + 1) % 4;
					x += dx[caseNum];
					y += dy[caseNum];
				}
				if (board[x][y] == 0 && caseNum == 0) {
					board[x][y] = num;
					y++;
					num++;
				}
				else if (board[x][y] == 0 && caseNum == 1) {
					board[x][y] = num;
					x++;
					num++;
				}else if (board[x][y] == 0 && caseNum == 2) {
					board[x][y] = num;
					y--;
					num++;
				}else if (board[x][y] == 0 && caseNum == 3) {
					board[x][y] = num;
					x--;
					num++;
				}else {
					x -= dx[caseNum];
					y -= dy[caseNum];
					caseNum = (caseNum + 1) % 4;
					x += dx[caseNum];
					y += dy[caseNum];
				}
			}
		
			System.out.println("#" + i);
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					System.out.print(board[j][k] + " ");
				}
				System.out.println();
			}
		}
	}

}
