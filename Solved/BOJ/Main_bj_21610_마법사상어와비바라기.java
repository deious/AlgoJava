import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] board;
	static boolean[][] check;
	
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	static ArrayDeque<data> q;
	
	static class data{
		int x, y;
		data(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static boolean InRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	static void MoveCloudAndRainDrop(int dir, int move) {
		for (data d : q) {
			d.x = (N + d.x + dx[dir] * (move % N)) % N;
			d.y = (N + d.y + dy[dir] * (move % N)) % N;
			board[d.x][d.y]++;
		}
	}
	
	static void DeleteCloud() {
		while(!q.isEmpty()) {
			data d = q.poll();
			int cnt = 0;
			check[d.x][d.y] = true;
			
			for (int i = 1; i <= 7; i+=2) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				
				if (InRange(nx, ny) && board[nx][ny] > 0) {
					cnt++;
				}
			}
			board[d.x][d.y] += cnt;
		}
	}
	
	static void CopyWater() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!check[i][j] && board[i][j] >= 2) {
					board[i][j] -= 2;
					q.offer(new data(i, j));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		q = new ArrayDeque<data>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		q.offer(new data(N - 2, 0));
		q.offer(new data(N - 2, 1));
		q.offer(new data(N - 1, 0));
		q.offer(new data(N - 1, 1));
		
		int dir = 0, move = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			dir = Integer.parseInt(st.nextToken()) - 1;
			move = Integer.parseInt(st.nextToken());
			
			check = new boolean[N][N];
			
			MoveCloudAndRainDrop(dir, move);
			DeleteCloud();
			CopyWater();
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer += board[i][j];
			}
		}
		
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}