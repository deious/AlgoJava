import java.io.*;
import java.util.*;

public class Main {

	static class Node {
		Map<Character, Node> chiledNode = new HashMap<Character, Node>();
		boolean endOfword;
	}

	static class Trie {
		Node rootNode = new Node();
		void insert(String str) {
			Node node = this.rootNode;

			for (int i = 0; i < str.length(); i++) {
				node = node.chiledNode.computeIfAbsent(str.charAt(i), key -> new Node());
			}
			node.endOfword = true;
		}

		boolean search(String str) {
			Node node = this.rootNode;

			for (int i = 0; i < str.length(); i++) {
				node = node.chiledNode.getOrDefault(str.charAt(i), null);
				if (node == null) {
					return false;
				}
			}

			return node.endOfword;
		}
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N, M, answer = 0;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		String s = "";
		Trie trie = new Trie();
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			trie.insert(s);
		}
		
		for (int i = 0; i < M; i++) {
			s = br.readLine();
			if (trie.search(s)) {
				answer++;
			}
		}
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}