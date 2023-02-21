import java.io.*;
import java.util.*;

public class Main {

	static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
	static int qCnt = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			q.add(i);
		}
		
		int temp;
		while(!q.isEmpty()) {
			qCnt++;
			temp = q.poll();
			
			if (qCnt == K) {
				if (q.isEmpty()) {
					sb.append(temp + ">");
				}
				else {
					sb.append(temp + ", ");
				}
				
				qCnt = 0;
			}else {
				q.add(temp);
			}
		}
		System.out.println(sb);
		br.close();
	}

}
