import java.io.*;
import java.util.*;

public class Main {

	static int[][] board;
	static int[] arr;
	static int[] bCnt;
	static int N, M, one = 0, two = 0, three = 0;
	static boolean check;
	
	static ArrayDeque<pair> q;

	static int dx[] = { 0, 1, 0, -1 }; // 좌, 하, 우, 상
	static int dy[] = { -1, 0, 1, 0 };

	static int dix[] = { 0, -1, 1, 0, 0 }; // 상 하 좌 우
	static int diy[] = { 0, 0, 0, -1, 1 };

	static class pair{
		int x, y;
		pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static void TDtoOD() {
		int len = N * N;
		int x = N / 2, y = N / 2;
		int dir = 0;
		int maxNum = 1;
		int moveCnt = 0;
		int temp = 0;
		int maxLen = 2;
		for (int i = 0; i < len; i++) {
			arr[i] = board[x][y];
			
			x += dx[dir];
			y += dy[dir];
			
			moveCnt++;
			temp++;
			
			if (temp == maxNum) {
				dir = (dir + 1) % 4;
				temp = 0;
			}

			if (moveCnt == maxLen) {
				maxLen += 2;
				maxNum++;
				moveCnt = 0;
			}
		}
	}

	static void ODtoTD() {
		int len = N * N;
		int x = N / 2, y = N / 2;
		int dir = 0;
		int maxNum = 1;
		int moveCnt = 0;
		int temp = 0;
		int maxLen = 2;
		for (int i = 0; i < len; i++) {
			board[x][y] = arr[i];
			
			x += dx[dir];
			y += dy[dir];
			
			moveCnt++;
			temp++;
			
			if (temp == maxNum) {
				dir = (dir + 1) % 4;
				temp = 0;
			}

			if (moveCnt == maxLen) {
				maxLen += 2;
				maxNum++;
				moveCnt = 0;
			}
		}
	}

	static void Move() { // 처음을 제외하고 0이 아닌곳을 찾다가 찾으면 거기서부터 0이 아닌 인덱스를 탐색 후 덮어 씌우기 마지막까지 검사했으면 더이상 검사 X
		int len = N * N;
		for (int i = 0; i < len; i++) {
			for (int j = 1; j < len - 1; j++) {
				if (arr[j] == 0) {
					arr[j] = arr[j + 1];
					arr[j + 1] = 0;
				}
			}
		}
	}
	
	static void Boom() {
		int len = N * N;
		int temp = arr[1];
		int tempi = 1;
		int endi = 0;
		bCnt[temp]++;
		for (int i = 2; i < len; i++) {
			if (temp == arr[i]) {
				bCnt[temp]++;
				endi = i;
			} else {
				if (bCnt[temp] >= 4 && temp != 0) {
					check = true;
					for (int j = tempi; j <= endi; j++) {
						arr[j] = 0;
					}
					
					if (temp == 1) {
						one += bCnt[temp];
					} else if (temp == 2) {
						two += bCnt[temp];
					} else {
						three += bCnt[temp];
					}
				}
				bCnt[temp] = 0;
				temp = arr[i];
				bCnt[temp]++;
				tempi = i;
			}
		}
		
		if (bCnt[temp] >= 4 && temp != 0) {
			check = true;
			for (int j = tempi; j < len; j++) {
				arr[j] = 0;
			}
			
			if (temp == 1) {
				one += bCnt[temp];
			} else if (temp == 2) {
				two += bCnt[temp];
			} else {
				three += bCnt[temp];
			}
		}
		bCnt[temp] = 0;
		
		check = !check;

	}
	
	static void Rematch() {
		int len = N * N;
		int temp = arr[1];
		int tempCnt = 0;
		for (int i = 1; i < len; i++) {
			if (arr[i] == 0) {
				break;
			} else if (arr[i] == temp) {
				tempCnt++;
			} else {
				q.offer(new pair(tempCnt, temp));
				temp = arr[i];
				tempCnt = 1;
			}
		}
		
		q.offer(new pair(tempCnt, temp));
		
		int idx = 1;
		while(!q.isEmpty() && idx < len) {
			pair p = q.poll();
			arr[idx++] = p.x;
			arr[idx++] = p.y;
		}
		
		while(!q.isEmpty()) {
			q.poll();
		}
		
		for (int i = idx; i < len; i++) {
			arr[i] = 0;
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
		arr = new int[N * N];
		q = new ArrayDeque<pair>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int d = 0, s = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			int startX = N / 2;
			int startY = N / 2;
			int tempX = startX;
			int tempY = startY;
			
			bCnt = new int[4];
			check = false;
			
			for (int j = 0; j < s; j++) {
				tempX += dix[d];
				tempY += diy[d];
				board[tempX][tempY] = 0;
			}
			TDtoOD();
			Move();
			ODtoTD();
			
			while(!check) {
				check = false;
				Boom();
				Move();
			}
			Rematch();
			ODtoTD();
			
		}
		
		int answer = one + (two * 2) + (three * 3);
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}