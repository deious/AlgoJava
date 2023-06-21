import java.awt.*;
import java.io.*;
import java.util.*;

class Coordinate{
	int x, y;
	
	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int N, M, minDist = 2100000000, homeSize, shopSize;
	static ArrayList<Coordinate> shop = new ArrayList<Coordinate>();
	static ArrayList<Coordinate> home = new ArrayList<Coordinate>();
	static Coordinate[] c;
	

	static int CalDist(Coordinate c1, Coordinate c2) {
		return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
	}

	static void comb(int start, int cnt) {
		if (cnt == M) {
			int temp = 0;
			for (int i = 0; i < homeSize; i++) {
				int tempDist = 2100000000;
				for (int j = 0; j < M; j++) {
					tempDist = Math.min(CalDist(home.get(i), c[j]), tempDist);
				}
				temp += tempDist;
			}
			
			if (minDist > temp) {
				minDist = temp;
			}
			return;
		}

		for (int i = start; i < shopSize; i++) {
			c[cnt] = shop.get(i);
			comb(i + 1, cnt + 1);
		}
		
		return;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		c = new Coordinate[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int temp;
			for (int j = 0; j < N; j++) {
				temp = Integer.parseInt(st.nextToken());
				if (temp == 1) {
					home.add(new Coordinate(i, j));
					homeSize++;
				} else if (temp == 2) {
					shop.add(new Coordinate(i, j));
					shopSize++;
				}
			}
		}
		
		comb(0, 0);
		sb.append(minDist);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}