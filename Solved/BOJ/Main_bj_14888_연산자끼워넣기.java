import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int[] operators;
	static int[] nums;
	static int maxNum, minNum, N;

	static void calcNum(int num, int[] operators, int index) {
		if (index == N - 1) {
			if (minNum > num)
				minNum = num;
			if (maxNum < num)
				maxNum = num;

			return;
		} else {
			if (operators[0] > 0) {
				operators[0]--;
				calcNum(num + nums[index + 1], operators, index + 1);
				operators[0]++;
			}
			if (operators[1] > 0) {
				operators[1]--;
				calcNum(num - nums[index + 1], operators, index + 1);
				operators[1]++;
			}
			if (operators[2] > 0) {
				operators[2]--;
				calcNum(num * nums[index + 1], operators, index + 1);
				operators[2]++;
			}
			if (operators[3] > 0) {
				operators[3]--;
				calcNum(num / nums[index + 1], operators, index + 1);
				operators[3]++;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		operators = new int[4];
		st = new StringTokenizer(br.readLine());
		nums = new int[N];
		for (int j = 0; j < N; j++) {
			nums[j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < 4; j++) {
			operators[j] = Integer.parseInt(st.nextToken());
		}
	
		maxNum = -2100000000;
		minNum = 2100000000;

		calcNum(nums[0], operators, 0);
		sb.append(maxNum).append("\n").append(minNum).append("\n");
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}