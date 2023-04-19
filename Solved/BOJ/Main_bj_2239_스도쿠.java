import java.io.*;
import java.util.*;

public class Main {

	static ArrayList<int[]> empty_spots = new ArrayList();
	static int[][] board = new int[9][9];

	static boolean sudoku(int count) {
		if (count == empty_spots.size()) {
			return true;
		} else {
			int[] position = empty_spots.get(count);
			int n = position[0];
			int m = position[1];
			for (int i = 1; i < 10; i++) {
				if (isPromising(board, n, m, i)) {
					board[n][m] = i;
					if (sudoku(count + 1))
						return true;
					else
						board[n][m] = 0;
				}
			}
		}
		return false;
	}

	static boolean isPromising(int[][] board, int n, int m, int num) {
		int len = board.length;
		int n_block = n / 3;
		int m_block = m / 3;

		for (int i = 0; i < len; i++) {
			if (board[n][i] == num)
				return false;
			if (board[i][m] == num)
				return false;
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[(n_block * 3) + i][(m_block * 3) + j] == num)
					return false;
			}
		}

		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String s = "";

		for (int i = 0; i < 9; i++) {
			s = br.readLine();
			for (int j = 0; j < 9; j++) {
				board[i][j] = s.charAt(j) - '0';
				if (board[i][j] == 0)
					empty_spots.add(new int[] { i, j });
			}
		}

		sudoku(0);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}