import java.io.*;
import java.util.*;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int sum = 2100000000;
		StringTokenizer st = new StringTokenizer(br.readLine(), "-");
		//System.out.println(st.nextToken());
		while (st.hasMoreTokens()) {
			int temp = 0;
 
			StringTokenizer addToken = new StringTokenizer(st.nextToken(), "+");
			
			while (addToken.hasMoreTokens()) {
				temp += Integer.parseInt(addToken.nextToken());
			}
			
			if (sum == 2100000000) {
				sum = temp;
			} else {
				sum -= temp;
			}
		}
		sb.append(sum);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
 
}