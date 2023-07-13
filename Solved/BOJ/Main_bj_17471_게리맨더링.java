import java.io.*;
import java.util.*;

public class Main{

	static int[] cityPopulation;
	static boolean[] group;
	static boolean[][] graph;
	static boolean[] check;
	static int N, ans = 2100000000;
	
	static void Perm(int cnt) {
		if (cnt == N) {
			if (GroupCheck()) {
				int start = 0;
				for (int i = 1; i <= N; i++) {
					if (!check[i]) {
						start = i;
						break;
					}
				}
				GroupBDFS(start);
				
				boolean abCheck = false;
				for (int i = 1; i <= N; i++) {
					if (!check[i]) {
						abCheck = true;
						break;
					}
				}
				if (!abCheck) {
					IntervalPopulation();
				}
			}
			return;
		}
		
		group[cnt + 1] = true;
		Perm(cnt + 1);
		group[cnt + 1] = false;
		Perm(cnt + 1);
	}
	
	static boolean GroupCheck() {
		check = new boolean[N + 1];
		int start = 0;
		for (int i = 1; i <= N; i++) {
			if (group[i] = true) {
				start = i;
				break;
			}
		}
		
		GroupADFS(start);
		
		int checkCount = 0;
		for (int i = 1; i <= N; i++) {
			if (group[i] == check[i]) {
				checkCount++;
			}
		}
		if (checkCount == N) {
			return true;
		}
		
		return false;
	}
	
	static void IntervalPopulation() {
		int ga = 0, gb = 0;
		for (int i = 1; i <= N; i++) {
			if (group[i]) {
				ga += cityPopulation[i];
			}else {
				gb+=cityPopulation[i];
			}
		}
		
		if (Math.abs(ga - gb) < ans) {
			ans = Math.abs(ga - gb);
		}
		
		return;
	}
	
	private static void GroupADFS(int x) {
		check[x] = true;
		for (int i = 1; i <= N; i++) {
			if (!check[i] && graph[x][i] && group[i]) {
				GroupADFS(i);
			}
		}
	}
	
	private static void GroupBDFS(int x) {
		check[x] = true;
		for (int i = 1; i <= N; i++) {
			if (!check[i] && graph[x][i] && !group[i]) {
				GroupBDFS(i);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		cityPopulation = new int[N + 1];
		group = new boolean[N + 1];
		st = new StringTokenizer(s = br.readLine());
		graph = new boolean[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			cityPopulation[i] = Integer.parseInt(st.nextToken());
		}
		
		int len = 0, temp = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(s = br.readLine());
			len = Integer.parseInt(st.nextToken());
			
			for (int j = 1; j <= len; j++) {
				temp = Integer.parseInt(st.nextToken());
				graph[i][temp] = true; 
			}
		}
		
		Perm(0);
		if (ans == 2100000000) {
			ans = -1;
		}
		System.out.println(ans);
        br.close();
	}

}
