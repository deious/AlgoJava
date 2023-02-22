import java.io.*;
import java.util.*;

public class Solution {

	static char board[][];
	static int H, W, dir;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static boolean InRange(int x, int y) {
		return x >= 0 && x < H && y >= 0 && y < W;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			board = new char[H][W];

			int sx = 0, sy = 0;
			for (int j = 0; j < H; j++) {
				String s = br.readLine();
				for (int k = 0; k < W; k++) {
					board[j][k] = s.charAt(k);
					if (board[j][k] == '^') {
						sx = j;
						sy = k;
						dir = 0;
					} else if (board[j][k] == 'v') {
						sx = j;
						sy = k;
						dir = 1;
					} else if (board[j][k] == '<') {
						sx = j;
						sy = k;
						dir = 2;
					} else if (board[j][k] == '>') {
						sx = j;
						sy = k;
						dir = 3;
					}
				}
			}

			int num = Integer.parseInt(br.readLine());
			String s = br.readLine();
			for (int j = 0; j < num; j++) {
				char c = s.charAt(j);
				if (c == 'U') {
					board[sx][sy] = '^';
					if (InRange(sx - 1, sy) && board[sx - 1][sy] == '.') {
						board[sx][sy] = '.';
						board[--sx][sy] = '^';
					}
					dir = 0;
				} else if (c == 'D') {
					board[sx][sy] = 'v';
					if (InRange(sx + 1, sy) && board[sx + 1][sy] == '.') {
						board[sx][sy] = '.';
						board[++sx][sy] = 'v';
					}
					dir = 1;
				} else if (c == 'L') {
					board[sx][sy] = '<';
					if (InRange(sx, sy - 1) && board[sx][sy - 1] == '.') {
						board[sx][sy] = '.';
						board[sx][--sy] = '<';
					}
					dir = 2;
				} else if (c == 'R') {
					board[sx][sy] = '>';
					if (InRange(sx, sy + 1) && board[sx][sy + 1] == '.') {
						board[sx][sy] = '.';
						board[sx][++sy] = '>';
					}
					dir = 3;
				} else {
					int bx = sx, by = sy;
					while (InRange(bx, by)) {
						bx += dx[dir];
						by += dy[dir];
						
						if (!InRange(bx, by)) {
							break;
						}
						if (board[bx][by] == '*' || board[bx][by] == '#') {
							if (board[bx][by] == '*') {
								board[bx][by] = '.';
							}
							break;
						}
					}
				}
			}
			sb.append("#").append(i).append(" ");
			for (int j = 0; j < H; j++) {
				for (int k = 0; k < W; k++) {
					sb.append(board[j][k]);
				}
				sb.append("\n");
			}
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
