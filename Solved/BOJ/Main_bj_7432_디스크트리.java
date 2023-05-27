import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder sb = new StringBuilder();
	
	static class TrieNode{
		Map<String, TrieNode> childNode = new HashMap<>();
		
		public void insert(String strs) {
			TrieNode trieNode = this;
			String[] str = strs.split("\\\\");
			for(String s : str) {
				trieNode.childNode.putIfAbsent(s, new TrieNode());
				trieNode = trieNode.childNode.get(s);
			}
		}
		
		public void print(TrieNode cur, int depth) {
			TrieNode trieNode = cur;
			if(trieNode.childNode !=null) {
				List<String> list = new ArrayList<>(trieNode.childNode.keySet());
				Collections.sort(list);
				for(String str : list) {
					for(int i=0; i<depth; i++) {
						sb.append(" ");
					}
					sb.append(str).append("\n");
					print(trieNode.childNode.get(str), depth+1);
				}
			}
			
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		
		TrieNode trie = new TrieNode();
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			trie.insert(line);
		}
		
		trie.print(trie, 0);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}