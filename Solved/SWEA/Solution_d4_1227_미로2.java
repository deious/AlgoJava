package ex;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class Solution_d4_20230215_미로2_서울_20반_김한성 {

	static int[][] maze;
	static boolean[][] check;
	static boolean goal = false;

	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };

	static int startX, startY, endX, endY;

	static boolean InRange(int x, int y) {
		return x >= 0 && x < 100 && y >= 0 && y < 100;
	}

	static void DFS(int x, int y) {
		int nx, ny;

		if (goal) {
			return;
		}

		if (maze[x][y] == 3) {
			goal = true;
			return;
		}

		for (int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];

			if (InRange(nx, ny) && !check[nx][ny] && (maze[nx][ny] == 0 || maze[nx][ny] == 3)) {
				check[nx][ny] = true;
				DFS(nx, ny);
			}
		}
	}

	static void BFS(int x, int y) {

		ArrayDeque<Point> q = new ArrayDeque<>();
		q.add(new Point(x, y));

		while (!q.isEmpty()) {
			Point p = q.poll();
			if (maze[p.x][p.y] == 3) {
				goal = true;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if (InRange(nx, ny) && !check[nx][ny] && (maze[nx][ny] == 0 || maze[nx][ny] == 3)) {
					check[nx][ny] = true;
					q.add(new Point(nx, ny));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String s;

		for (int i = 1; i <= 10; i++) {
			br.readLine();
			goal = false;
			maze = new int[100][100];
			check = new boolean[100][100];
			for (int j = 0; j < 100; j++) {
				s = br.readLine();
				for (int k = 0; k < 100; k++) {
					maze[j][k] = s.charAt(k) - '0';
					if (maze[j][k] == 2) {
						startX = j;
						startY = k;
					} else if (maze[j][k] == 3) {
						endX = j;
						endY = k;
					}
				}
			}

			check[startX][startY] = true;
			DFS(startX, startY);
			//BFS(startX, startY);

			if (goal) {
				sb.append("#").append(i).append(" ").append(1).append("\n");
			} else {
				sb.append("#").append(i).append(" ").append(0).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}
