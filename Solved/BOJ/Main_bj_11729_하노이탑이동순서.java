import java.util.Scanner;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int moveCnt = 0;
	
	static void hanoi(int n, int from, int mid, int to) {
		if (n == 0) {
			return;
		}
		else {
			hanoi(n - 1, from, to, mid);
			moveCnt++;
			sb.append(from).append(" ").append(to).append("\n");
			hanoi(n - 1, mid, from, to);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		hanoi(n, 1, 2, 3);
		System.out.println(moveCnt);
		System.out.println(sb);
		sc.close();
	}

}