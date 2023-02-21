import java.io.*;
import java.util.*;

public class Main {

	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Stack<Integer> s = new Stack<Integer>();
		Stack<Integer> s1 = new Stack<Integer>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int temp;
		for (int i = 1; i <= N; i++) {
			if (i == 1) {
				sb.append(0).append(" ");
				s.add(Integer.parseInt(st.nextToken()));
				s1.add(i);
			} else {
				temp = Integer.parseInt(st.nextToken());
				if (s.peek() < temp) {
					s.pop();
					s1.pop();
					while (!s.isEmpty()) {
						if (s.peek() < temp) {
							s.pop();
							s1.pop();
						}else {
							break;
						}
					}
					
					if (s1.size() == 0) {
						sb.append(0).append(" ");
					} else {
						sb.append(s1.peek()).append(" ");
					}
				} else {
					sb.append(s1.peek()).append(" ");
				}

				s.add(temp);
				s1.add(i);
			}
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
