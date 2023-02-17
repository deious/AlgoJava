package ex;

import java.io.*;
import java.util.*;

public class Solution_모의SW역량테스트_20230216_요리사_서울_20반_김한성 {

	static int[][] board;
	static int[] perArr = new int[2];
	static int[] arr;
	static boolean[] check;
	static int T, N, R = 2, answer = 2100000000;
	static ArrayList<Integer> save = new ArrayList<>();

	static boolean nextPermutation(int[] arr) {

		int i = N - 1;
		while (i > 0 && arr[i - 1] >= arr[i])
			--i;

		if (i == 0)
			return false;

		int j = N - 1;
		while (arr[i - 1] >= arr[j])
			--j;

		int temp = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = temp;

		int k = N - 1;
		while (i < k) {
			temp = arr[i];
			arr[i] = arr[k];
			arr[k] = temp;
			++i;
			--k;
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			arr = new int[N];
			answer = 2100000000;

			int len = N / 2;
			for (int j = len; j < N; j++) {
				arr[j] = 1;
			}

			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					board[j][k] = Integer.parseInt(st.nextToken());
				}
			}

			do {
				int temp = 0, temp1 = 0;
				ArrayList<Integer> save = new ArrayList<>(), save1 = new ArrayList<>();
				for (int j = 0; j < N; j++) {
					if (arr[j] == 0) {
						save.add(j);
					} else {
						save1.add(j);
					}
				}
				
				for (int j = 0; j < len; j++) {
					for (int k = 0; k < len; k++) {
						if (j == k) {
							continue;
						}
						
						temp += board[save.get(j)][save.get(k)];
						temp1 += board[save1.get(j)][save1.get(k)];
					}

				}
				answer = Math.min(Math.abs(temp - temp1), answer);
			} while (nextPermutation(arr));

			sb.append("#").append(i).append(" ").append(answer).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
