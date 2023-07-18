import java.io.*;
import java.util.*;

public class Main {
	
	static int N, K, durationCheck, roop = 0, lastIdx;
	static ArrayList<Integer> duration;
	static ArrayList<Boolean> belt;
	
	static void Rotate() {
		belt.add(0, belt.get(lastIdx));
		belt.remove(belt.size() - 1);
		belt.set(N - 1, false);
		
		duration.add(0, duration.get(lastIdx));
		duration.remove(duration.size() - 1);
	}
	
	static void MoveRobot() {
		int start = N - 2;
		for (int i = start; i >= 0; i--) {
			if (belt.get(i) && !belt.get(i + 1) && duration.get(i + 1) > 0) {
				belt.set(i, false);
				belt.set(i + 1, true);
				duration.set(i + 1, duration.get(i + 1) - 1);
			}
		}
		belt.set(N - 1, false);
	}
	
	static void PutRobot() {
		if (!belt.get(0) && duration.get(0) > 0) {
			belt.set(0, true);
			duration.set(0, duration.get(0) - 1);
		}
	}
	
	static int DurationCheck() {
		durationCheck = 0;
		int len = duration.size();
		
		for(int i = 0; i < len; i++) {
			if (duration.get(i) <= 0) {
				durationCheck++;
			}
		}
		return durationCheck;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int len = N * 2;
		lastIdx = N * 2 - 1;
		duration = new ArrayList<Integer>();
		belt = new ArrayList<Boolean>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) {
			duration.add(Integer.parseInt(st.nextToken()));
			belt.add(false);
		}
		
		while(true) {
			roop++;
			Rotate();
			MoveRobot();
			PutRobot();
			
			if (DurationCheck() >= K) {
				break;
			}
		}

		sb.append(roop);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
