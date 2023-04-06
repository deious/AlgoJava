import java.io.*;
import java.util.*;

public class Solution {
	
	public static long pow(long a, long b){
        if(b == 0)
            return 1;
        long ans = pow(a, b / 2);
        long next = (ans * ans) % 1234567891;
        if(b % 2 == 0)
            return next;
        else
            return (next * a) % 1234567891;
    }

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		long[] factorial = new long[1000001];
        factorial[0] = 1;
        for(int i = 1; i <= 1000000; i++){
            factorial[i] = (i * factorial[i - 1]) % 1234567891;
        }
        
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
 
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
 
            long ans = (factorial[N] * pow( (factorial[R]*factorial[N-R]) % 1234567891, 1234567891-2)) % 1234567891;
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
