import java.io.*;
import java.util.*;

public class Main{

	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static int N, M, K;

	static ArrayDeque<int[][]> tree = new ArrayDeque<int[][]>();
	static int[][] defaultEnergy;
	static int[][] addEnergy;

	static boolean InRange(int x, int y) {
		return x >= 1 && x <= N && y >= 1 && y <= N;
	}

	static void SpringSummer() {
		int len = tree.size();
		int x, y, age;
		int[][] temp;
		int[][] saveEnergy = new int[N + 1][N + 1];
		for (int i = 0; i < len; i++) {
			temp = tree.poll();
			x = temp[0][0];
			y = temp[0][1];
			age = temp[1][0];

			if (defaultEnergy[x][y] >= age) {
				defaultEnergy[x][y] -= age;
				tree.add(new int[][] { { x, y }, { age + 1 } });
			} else {
				saveEnergy[x][y] += age / 2;
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (saveEnergy[i][j] != 0) {
					defaultEnergy[i][j] += saveEnergy[i][j];
				}
			}
		}
	}

	static void Fall() {
		ArrayDeque<int[][]> tempTree = new ArrayDeque<int[][]>();
		int x, y, age, len = tree.size();
		int[][] temp;
		for (int i = 0; i < len; i++) {
			temp = tree.poll();
			x = temp[0][0];
			y = temp[0][1];
			age = temp[1][0];

			tree.add(temp);
			if (age % 5 != 0) {
				continue;
			}

			for (int j = 0; j < 8; j++) {
				int nx = x + dx[j];
				int ny = y + dy[j];

				if (InRange(nx, ny)) {
					tempTree.add(new int[][] { { nx, ny }, { 1 } });
				}
			}
		}

		while (!tempTree.isEmpty()) {
			tree.addFirst(tempTree.poll());
		}
	}

	static void Winter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				defaultEnergy[i][j] += addEnergy[i][j];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringTokenizer st = new StringTokenizer(s = br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int year = 0, answer = 0;
		int[][] temp;
		
		addEnergy = new int[N + 1][N + 1];
		defaultEnergy = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(s = br.readLine());
			for (int j = 1; j <= N; j++) {
				addEnergy[i][j] = Integer.parseInt(st.nextToken());
				defaultEnergy[i][j] = 5;
			}
		}

		int x, y, treeNum;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(s = br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			treeNum = Integer.parseInt(st.nextToken());

			tree.add(new int[][] { { x, y }, { treeNum } });
		}

		while (year != K) {
			SpringSummer();
			Fall();
			Winter();
			year++;
		}

		if (year == K) {
			answer = tree.size();
		}
		System.out.println(answer);
		br.close();
	}

}
