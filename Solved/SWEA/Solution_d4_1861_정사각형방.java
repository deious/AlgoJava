package ex;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class Solution_d4_20230215_정사각형방_서울_20반_김한성 {
	static int[][] board;														// 전체 보드
	static int[][] dp;															// 시작 좌표의 최대값 저장용 배열
	static int[][] moveBoard;													// 이동 횟수 기록 배열
	static int N, dfsCnt = 0, bfsCnt = 0, room = 2100000000, move = 0;
	
	static int dx[] = {0, 0, -1, 1};											// 사방탐색용
	static int dy[] = {-1, 1, 0, 0};
	
	static boolean InRange(int x, int y) {										// 범위체크
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	static void DFS(int x, int y) {												// DFS
		int nx, ny;
		if (moveBoard[x][y] == 0) {												// 처음 보드 시작 시 횟수 1로 변경
			moveBoard[x][y] = 1;
		}
		dfsCnt++;
		for (int i = 0; i < 4; i++) {											// 사방탐색
			nx = x + dx[i];
			ny = y + dy[i];
			if (InRange(nx, ny) && (board[x][y] + 1 == board[nx][ny]) && moveBoard[x][y] + 1 > moveBoard[nx][ny]) {
				moveBoard[nx][ny] = moveBoard[x][y] + 1;
				DFS(nx, ny);
			}
		}
		
		dp[x][y] = dfsCnt;
	}
	
	static void BFS(int x, int y) {
		int nx, ny;
		ArrayDeque<Point> q = new ArrayDeque<Point>();
		q.add(new Point(x, y));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			bfsCnt++;
			
			for (int i = 0; i < 4; i++) {
				nx = p.x + dx[i];
				ny = p.y + dy[i];
				
				if (InRange(nx, ny) && (board[p.x][p.y] + 1 == board[nx][ny])) {
					q.add(new Point(nx, ny));
				}
			}
		}
		
		dp[x][y] = bfsCnt;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T;
		T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			dp = new int[N][N];
			moveBoard = new int[N][N];
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					board[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			move = 0;
			room = 2100000000;
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (moveBoard[j][k] == 0) {
						dfsCnt = 0;
						DFS(j, k);
					}
					if (dp[j][k] > move) {
						move = dp[j][k];
						room = board[j][k];
					} else if (dp[j][k] == move) {
						if (room > board[j][k]) {
							room = board[j][k];
						}
					}
				}
			}
			
			/*for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					bfsCnt = 0;
					BFS(j, k);
					if (dp[j][k] > move) {
						move = dp[j][k];
						room = board[j][k];
					} else if (dp[j][k] == move) {
						if (room > board[j][k]) {
							room = board[j][k];
						}
					}
				}
			}*/
			sb.append("#").append(i).append(" ").append(room).append(" ").append(move).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}
