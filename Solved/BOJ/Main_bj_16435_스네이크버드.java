import java.io.*;
import java.util.*;

public class Main{
	
	static int[] fruit;
	static int N, L;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		fruit = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			fruit[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(fruit);
		
		int index = 0;
		while(true) {
			if (fruit[index] <= L) {
				index++;
				L++;
			}
			else {
				sb.append(L);
				break;
			}
			
			if (index == N) {
				sb.append(L);
				break;
			}
		}
		bw.write(sb.toString());
		bw.close();
		br.close();	
	}
}