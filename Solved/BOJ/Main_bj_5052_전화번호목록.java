import java.io.*;
import java.util.*;

public class Main{
	
	static class TrieNode {
        Map<Character, TrieNode> childNodes = new HashMap<>();
        boolean isLast;
    }

    static class Trie {
        TrieNode root = new TrieNode();

        boolean insert(String word) {
            TrieNode thisNode = root;
            
            for (int i = 0; i < word.length(); i++) {
                char n = word.charAt(i);
                if (thisNode.childNodes.get(n) == null) {
                    thisNode.childNodes.put(n, new TrieNode());
                }
                thisNode = thisNode.childNodes.get(n);
                if (thisNode.isLast) {
                	return false;
                }
            }
            if (thisNode.childNodes.size() != 0) {
            	return false;
            }
            
            thisNode.isLast = true;
            return true;
        }
    }
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            Trie trie = new Trie();
            boolean flag = true;

            int n = Integer.parseInt(br.readLine());
            for (int j = 0; j < n; j++) {
                if(!trie.insert(br.readLine())) {
                    flag = false;
                    continue;
                }
            }
            sb.append(flag == true ? "YES\n" : "NO\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}