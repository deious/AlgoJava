import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N, ans = -2100000000;
	static String S;
	
	static int Calculate(int a, int b, char c)
	{
		if (c == '+')
			return a + b;
		else if (c == '-')
			return a - b;
		else
			return a * b;
	}

	static void Check(int index, int a)
	{
		if (index >= N)
		{
			ans = Math.max(ans, a);
			return;
		}

		char c = index > 0 ? S.charAt(index - 1) : '+';
		int temp = Calculate(a, S.charAt(index) - '0', c);

		Check(index + 2, temp);

		if (index < N - 2)
		{
			temp = Calculate(S.charAt(index) - '0', S.charAt(index + 2) - '0', S.charAt(index + 1));
			temp = Calculate(a, temp, c);

			Check(index + 4, temp);
		}

		return;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		S = br.readLine();
		
		Check(0, 0);
		
		sb.append(ans);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}