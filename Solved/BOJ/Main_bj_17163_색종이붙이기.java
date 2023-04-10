package bj;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static int board[][];
	static int confetti[];
	static int answer = 2100000000;

	static boolean InRange(int x, int y) {
		return x >= 0 && x < 10 && y >= 0 && y < 10;
	}
	
	static void BackTracking(int x, int y, int cnt) {		
        if (x > 9) {
            answer = Math.min(answer, cnt);
            return;
        }
 
        if (answer <= cnt) {
            return;
        }
 
        if (y > 9) {
        	BackTracking(x + 1, 0, cnt);
            return;
        }
 
        if (board[x][y] == 1) {
            for (int i = 5; i >= 1; i--) {
                if (confetti[i] > 0 && CoverCheck(x, y, i)) {
                    Cover(x, y, i);
                    confetti[i]--;
                    BackTracking(x, y + 1, cnt + 1);
                    UnCover(x, y, i);
                    confetti[i]++;
                }
            }
        } else {
        	BackTracking(x, y + 1, cnt);
        }
    }
 
    static void Cover(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                board[i][j] = 0;
            }
        }
    }
    
    static void UnCover(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                board[i][j] = 1;
            }
        }
    }
	
	static boolean CoverCheck(int x, int y, int size) {
		if (!InRange(x + size - 1, y + size - 1)) {
			return false;
		}
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		board = new int[10][10];
		confetti = new int[6];
		for (int i = 1; i <= 5; i++) {
			confetti[i] = 5;
		}

		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		BackTracking(0, 0, 0);

		if (answer == 2100000000) {
			answer = -1;
		}
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
