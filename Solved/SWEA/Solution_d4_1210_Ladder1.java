package ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_d4_20230208_Ladder1_서울_20반_김한성 {

	public static int[][] board = new int[100][100];
	public static boolean[][] check;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int sx, sy;
	
	static int dx[] = {0, 0, -1};
	static int dy[] = {-1, 1, 0};
	
	public static boolean InRange(int x, int y) {
		return x >= 0 && x < 100 && y >= 0 && y < 100;
	}
	
	public static int destination(int sx, int sy) {
		
		int nx = 0;
		int ny = 0;
		while(sx > 0) {
			for (int i = 0; i < 3; i++) {
				nx = sx + dx[i];
				ny = sy + dy[i];
				
				if (InRange(nx, ny) && !check[nx][ny] && board[nx][ny] == 1) {
					check[nx][ny] = true;
					sx = nx;
					sy = ny;
					break;
				}
			}
		}
		
		return sy;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			int num = Integer.parseInt(br.readLine());
			
			String s;
			StringTokenizer st;
			
			check = new boolean[100][100];
			for (int j = 0; j < 100; j++) {
				s = br.readLine();
				st = new StringTokenizer(s);
				
				for (int k = 0; k < 100; k++) {
					board[j][k] = Integer.parseInt(st.nextToken());
					
					if (board[j][k] == 2) {
						check[j][k] = true;
						sx = j;
						sy = k;
					}
				}
			}

			System.out.println("#" + num + " " + destination(sx, sy));
		}
		br.close();
	}

}
