import java.io.*;
import java.util.*;

public class Main {

	static int L, C;
	static char[] alpha;
	static char[] saveAlpha;
	static boolean[] check;
	
	static StringBuilder sb = new StringBuilder();
	
	static void Comb(int start, int cnt) {
		if (cnt == L) {
			if (CheckAlpha()) {
				sb.append(saveAlpha).append("\n");
			}
			return;
		}
		
		for (int i = start; i < C; i++) {
			if (check[i]) {
				continue;
			}
			
			check[i] = true;
			saveAlpha[cnt] = alpha[i];
			Comb(i + 1, cnt + 1);
			check[i] = false;
		}
	}
	
	static boolean CheckAlpha() {
		int vowel = 0, consonant = 0;
		for (int i = 0; i < L; i++) {
			if (saveAlpha[i] == 'a' || saveAlpha[i] == 'e' || saveAlpha[i] == 'i' || saveAlpha[i] == 'o' || saveAlpha[i] == 'u') {
				vowel++;
			} else {
				consonant++;
			}
			
			if (vowel > 0 && consonant > 1) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		alpha = new char[C];
		saveAlpha = new char[L];
		check = new boolean[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alpha[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(alpha);
		Comb(0, 0);

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}