package ex;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Solution_d4_20230213_치즈도둑_서울_20반_김한성_BFS {

	static int N, T, cheese = 0, temp = 0;
	static int[][] board;
	static boolean[][] check;
	
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {1, -1, 0, 0};
	
	static boolean InRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	static void BFS(int a, int b) {
		ArrayDeque<Point> q = new ArrayDeque<Point>();
		q.add(new Point(a, b));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int x = p.x;
			int y = p.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (InRange(nx, ny) && !check[nx][ny] && board[nx][ny] != 0) {
					check[nx][ny] = true;
					q.add(new Point(nx, ny));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			board = new int[N][N];
			cheese = 1;
			
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					board[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int j = 1; j <= 100; j++) {
				check = new boolean[N][N];
				temp = 0;
				for (int k = 0; k < N; k++) {
					for (int l = 0; l < N; l++) {
						if (board[k][l] == j) {
							board[k][l] = 0;
						}
					}
				}

				for (int k = 0; k < N; k++) {
					for (int l = 0; l < N; l++) {
						if (!check[k][l] && board[k][l] != 0) {
							check[k][l] = true;
							BFS(k, l);
							temp++;
						}
					}
				}
				
				if (temp > cheese) {
					cheese = temp;
				}
			}
			sb.append("#" + i + " " + cheese + "\n");
		}
		
		System.out.println(sb);
		br.close();
	}

}
