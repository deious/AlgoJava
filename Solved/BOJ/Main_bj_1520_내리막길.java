import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int dp[][], board[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };
	
	static boolean InRange(int x, int y) {
		return x >= 0 && x < M && y >= 0 && y < N;
	}
	
	static int DFS(int x, int y)
	{
		if (x == M - 1 && y == N - 1)
			return 1;
		if (dp[x][y] != -1)
			return dp[x][y];

		dp[x][y] = 0;

		for (int i = 0; i < 4; i++)
		{
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (InRange(nx, ny) && board[x][y] > board[nx][ny])
				dp[x][y] += DFS(nx, ny);
		}

		return dp[x][y];
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		dp = new int[M][N];
		board = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}	
		
		sb.append(DFS(0, 0));
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}