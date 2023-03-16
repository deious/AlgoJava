import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[] primeNum = {1, 2, 3, 5, 7, 9};
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	static void reculsive(int n, int cnt) {
		if (cnt == N) {
			if (IsPrime(n)) {
				sb.append(n + "\n");
			}
			return;
		}
		
		if (n == 0 || IsPrime(n)) {
			int temp;
			for (int i = 0; i < 6; i++) {
				temp = n * 10 + primeNum[i];
				reculsive(temp, cnt + 1);
			}
		}
	}
	
	static boolean IsPrime(int n) {
		if (n < 2) {
			return false;
		}
		
		int sq = (int)Math.sqrt(n);
		for (int i = 2; i <= sq; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		reculsive(0, 0);
		
		System.out.println(sb);
		br.close();
	}

}
