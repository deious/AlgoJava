import java.io.*;
import java.util.*;

public class Main {

	static int N, answer = 0;
	static int[][] board;
	static int[][] fBoard;
	static int[][] student;
	static int[][] near;
	static int[][] empty;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class data {
		int x, y, favorite, empty;
		data(int x, int y, int favorite, int empty){
			this.x = x;
			this.y = y;
			this.favorite = favorite;
			this.empty = empty;
		}
	}
	
	static class dataComp implements Comparator<data>{
		@Override
		public int compare(data o1, data o2) {
			// TODO Auto-generated method stub
			if (o1.favorite == o2.favorite) {
				return o2.empty - o1.empty;
			}
			else {
				return o2.favorite - o1.favorite;
			}
		}
		
	}
	
	static boolean InRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		ArrayList<data> temp;
		N = Integer.parseInt(br.readLine());
		
		int len = N * N;
		
		board = new int[N][N];
		student = new int[len + 1][4];
		
		int num = 0;
		for (int i = 0; i < len; i++) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			q.offer(num);
			for (int j = 0; j < 4; j++) {
				student[num][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int maxNear = 0, maxEmpty = 0;
		int neX = 0, neY = 0, nemX = 0, nemY = 0;
		
		for (int i = 0; i < len; i++) {
			int stu = q.poll();
			near = new int[N][N];
			empty = new int[N][N];
			maxNear = maxEmpty = 0;
			neX = neY = nemX = nemY = 0;
			temp = new ArrayList<data>();
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (board[j][k] == 0) {
						int nearCnt = 0;
						int emptyCnt = 0;
						for (int l = 0; l < 4; l++) {
							int nx = j + dx[l];
							int ny = k + dy[l];
							
							if (InRange(nx, ny)) {
								for (int m = 0; m < 4; m++) {
									if (board[nx][ny] == student[stu][m]) {
										nearCnt++;
									}
								}
								
								
								if (board[nx][ny] == 0) {
									emptyCnt++;
								}
							}
						}
						if (nearCnt > maxNear) {
							maxNear = nearCnt;
							neX = j;
							neY = k;
						}
						
						if (emptyCnt > maxEmpty) {
							maxEmpty = emptyCnt;
							nemX = j;
							nemY = k;
						}
						
						temp.add(new data(j, k, nearCnt, emptyCnt));
					}
				}
			}
			
			Collections.sort(temp, new dataComp());
			board[temp.get(0).x][temp.get(0).y] = stu;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int nCnt = 0;
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					if (InRange(nx, ny)) {
						for (int l = 0; l < 4; l++) {
							if (board[nx][ny] == student[board[i][j]][l]) {
								nCnt++;
							}
						}
					}
				}
				
				if (nCnt == 0) {
					answer += 0;
				} else if (nCnt == 1) {
					answer += 1;
				} else if (nCnt == 2) {
					answer += 10;
				} else if (nCnt == 3) {
					answer += 100;
				} else {
					answer += 1000;
				}
			}
		}
		
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}