package swea;

import java.io.*;
import java.util.*;

public class Solution {

	static class Stair {
		int r, c, k;

		Stair(int r, int c, int k) {
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}

	static class Person {
		int r, c;
		int stair;
		int arriveTime;
		int stairTime;

		Person(int r, int c) {
			this.r = r;
			this.c = c;
		}

		private void calArriveTime() {
			this.arriveTime = Math.abs(r - stairs[stair].r) + Math.abs(c - stairs[stair].c);
		}
	}

	private static void go(int idx) {

		if (idx == persons.size()) {
			visited = new boolean[persons.size()];

			int cur = simulation();

			ans = ans > cur ? cur : ans;
			return;
		}

		persons.get(idx).stair = 0;
		persons.get(idx).calArriveTime();
		go(idx + 1);

		persons.get(idx).stair = 1;
		persons.get(idx).calArriveTime();
		go(idx + 1);
	}

	private static int simulation() {
		int cnt = 0;
		int time = 1;

		while (true) {
			for (Queue<Person> q : qs) {
				int size = q.size();

				for (int i = 0; i < size; ++i) {
					Person p = q.poll();
					Stair s = stairs[p.stair];

					if (p.stairTime + s.k <= time) {
						continue;
					}

					q.offer(p);
				}
			}

			if (cnt == persons.size() && qs[0].isEmpty() && qs[1].isEmpty()) {
				return time;
			}

			for (int i = 0; i < persons.size(); ++i) {
				if (visited[i])
					continue;

				Person p = persons.get(i);
				Queue<Person> q = qs[p.stair];

				if (p.arriveTime + 1 <= time && q.size() < 3) {
					p.stairTime = time;
					visited[i] = true;
					q.offer(p);
					cnt++;
				}
			}
			time++;
		}
	}

	static Queue<Person>[] qs;
	static ArrayList<Person> persons;
	static boolean[] visited;
	static Stair[] stairs;
	static int N, T, ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; ++t) {

			N = Integer.parseInt(br.readLine());

			persons = new ArrayList<>();
			qs = new LinkedList[2];
			qs[0] = new LinkedList<>();
			qs[1] = new LinkedList<>();
			stairs = new Stair[2];
			ans = Integer.MAX_VALUE;

			int stairIdx = 0;

			for (int r = 1; r <= N; ++r) {
				st = new StringTokenizer(br.readLine());
				for (int c = 1; c <= N; ++c) {
					int num = Integer.parseInt(st.nextToken());

					if (num == 0) {
						continue;
					} else if (num == 1) {
						persons.add(new Person(r, c));
					} else {
						stairs[stairIdx] = new Stair(r, c, num);
						stairIdx++;
					}
				}
			}

			go(0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
