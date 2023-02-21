import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	
	static int N, M;
	public static int[] arr;
	public static void swap(int n) {
		for (int i = n - 1; i < N; i += n) {
			if (arr[i] != 0) {
				arr[i] = 0;
			}else {
				arr[i] = 1;
			}
		}
	}
	
	public static void check(int n) {
		int prevIdx = n - 2, nextIdx = n;
		
		while(nextIdx >= 0 && prevIdx < N) {
			if (prevIdx < 0 || nextIdx >= N) {
				prevIdx++;
				nextIdx--;
				break;
			}
			
			if (arr[prevIdx] != arr[nextIdx]) {
				prevIdx++;
				nextIdx--;
				break;
			}
			prevIdx--;
			nextIdx++;
		}

		
		for (int i = prevIdx; i <= nextIdx; i++) {
			if (arr[i] != 0) {
				arr[i] = 0;
			}else {
				arr[i] = 1;
			}
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		String s = in.readLine();
		StringTokenizer st = new StringTokenizer(s);
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(in.readLine());
		for (int i = 0; i < M; i++) {
			s = in.readLine();
			st = new StringTokenizer(s);
			if (Integer.parseInt(st.nextToken()) == 1) {
				swap(Integer.parseInt(st.nextToken()));
			}else {
				check(Integer.parseInt(st.nextToken()));
			}
		}
		
		for (int i = 0; i < N; i++) {
			System.out.print(arr[i] + " ");
			
			if ((i + 1) % 20 == 0) {
				System.out.println();
			}
		}
	}

}
