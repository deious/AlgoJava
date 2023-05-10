import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		Map<String, Integer> map = new TreeMap<>();
		String s;
		double cnt = 0;
		while((s = br.readLine()) != null && !s.isEmpty()) {
			cnt++;
			map.put(s, map.containsKey(s) ? map.get(s) + 1 : 1);
		}
		
		for (String key : map.keySet()) {
			sb.append(key).append(" ").append(String.format("%.4f", map.get(key) / cnt * 100)).append("\n");
		}
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}