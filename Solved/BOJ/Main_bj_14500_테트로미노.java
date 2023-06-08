import java.io.*;
import java.util.*;

public class Main {

	static int[][][] tBoard = {
			{{0,1}, {0,2}, {0,3}},
			{{1,0}, {2,0}, {3,0}},
			{{1,0}, {1,1}, {1,2}},
			{{0,1}, {1,0}, {2,0}},
			{{0,1}, {0,2}, {1,2}},
			{{1,0}, {2,0}, {2,-1}},
			{{0,1}, {0,2}, {-1,2}},
			{{1,0}, {2,0}, {2,1}},
			{{0,1}, {0,2}, {1,0}},
			{{0,1}, {1,1}, {2,1}},
			{{0,1}, {1,0}, {1,1}},
			{{0,1}, {-1,1}, {-1,2}},
			{{1,0}, {1,1}, {2,1}},
			{{0,1}, {1,1}, {1,2}},
			{{1,0}, {1,-1}, {2,-1}},
			{{0,1}, {0,2}, {-1,1}},
			{{0,1}, {0,2}, {1,1}},
			{{1,0}, {2,0}, {1,1}},
			{{1,0}, {2,0}, {1,-1}}
		};
	static int[][] board;
	static int N, M, answer = 0;
	
	static boolean InRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		boolean check = false;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < 19; k++) {
					check = true;
					int sum = board[i][j];
					
					for (int l = 0; l < 3; l++) {
						int x = i + tBoard[k][l][0];
						int y = j + tBoard[k][l][1];
						
						if (InRange(x, y)) {
							sum += board[x][y];
						} else {
							check = false;
							break;
						}
					}
					if (check && answer < sum) {
						answer = sum;
					}
				}
			}
		}
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}