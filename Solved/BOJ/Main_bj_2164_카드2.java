import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Queue<Integer> q = new ArrayDeque<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int temp;
		
		for (int i = 1; i <= N; i++) {
			q.add(i);
		}
		
		while(q.size() != 1) {
			q.remove();
			temp = q.poll();
			q.add(temp);
		}
		
		System.out.println(q.poll());
		br.close();
	}

}
