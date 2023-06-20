import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	static int dx[] = { 0, -1, 0, 1 };
	static int dy[] = { 1, 0, -1, 0 };

	static int[][] board = new int[101][101];

	static int N, x, y, d, g, ans = 0;

	static Vector<Integer> dir = new Vector<Integer>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(bf.readLine());
		while (N-- != 0) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());

			dir.clear();
			
			board[x][y] = 1;
			x += dx[d];
			y += dy[d];
			board[x][y] = 1;
			
			dir.add(d);
			
			for (int i = 0; i < g; i++) {
				MakeDragon();
			}
		}
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (board[i][j] == 1 && board[i][j + 1] == 1 && board[i + 1][j] == 1 && board[i + 1][j + 1] == 1) {
					ans++;
				}
			}
		}
		
		bw.write(ans + "\n");
        bf.close();
        bw.flush();
        bw.close();
	}
	public static void MakeDragon() {
		int len = dir.size();

		for (int i = len - 1; i >= 0; i--) {
			int nDir = (dir.get(i) + 1) % 4;
			x += dx[nDir];
			y += dy[nDir];
			board[x][y] = 1;
			dir.add(nDir);
		}
	}
}