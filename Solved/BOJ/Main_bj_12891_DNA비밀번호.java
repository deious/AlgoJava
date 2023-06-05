import java.io.*;
import java.util.*;

public class Main{
	static int S, P, answer;
	static String DNA;
	static HashMap<Character, int[]> hm = new HashMap<Character, int[]>();
	static char[] dna = {'A','C','G','T'};

    public static boolean isFull() {
		for (char c : dna) {
			if (hm.get(c)[0] < hm.get(c)[1])
                return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		DNA = br.readLine();
		st = new StringTokenizer(br.readLine());
        
		for (char c : dna) {
			hm.put(c, new int[] {0, Integer.parseInt(st.nextToken())});
		}
        
		for (int i = 0; i < P; i++) {
			hm.get(DNA.charAt(i))[0]++;
		}

		if(isFull())
            answer++;		
        
		for (int i = 0; i < S-P; i++) {
			hm.get(DNA.charAt(i))[0] -= 1;
			hm.get(DNA.charAt(i + P))[0] += 1;
			if(isFull())
                answer++;
		}
		System.out.println(answer);
		br.close();
	}
}