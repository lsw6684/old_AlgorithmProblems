/*
Description

총 N개의 정점(node)과 M개의 간선(edge)로 구성된 무방향 가중치 연결그래프 G가 존재한다.

G는 완전그래프(fully connected graph)이며, 두 정점을 연결하는 간선은 1개이고, 모든 간선들의 가중치 w는 서로 다른 고유한 정수값이다.

그래프 G의 최소 신장 트리(MST)를 구성하는 모든 간선들의 합을 구하는 프로그램을 작성하시오.


Input
첫째 줄에는 정점의 개수 N과 간선의 개수 M이 주어진다. (100≤ N≤ 200,  4,950≤ M≤ 19,900)

둘째 줄부터 M개의 줄에 각 간선의 양 끝 점의 인덱스 x, y와 가중치 w가 주어진다.(w ≤ 1,000,000)


Output
최소 신장 트리를 구성하는 모든 간선들의 가중치의 합을 출력한다.


Sample Input 1 

6 15
5 6 361
3 4 768
1 2 946
1 6 185
2 6 956
4 5 124
2 4 747
3 6 886
3 5 601
4 6 258
2 3 497
1 4 482
1 5 639
1 3 916
2 5 237
Sample Output 1

1301
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Comparator;

class Main {
	private static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(sc.readLine().trim());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		arr = new int[V + 1];
		for (int i = 1; i <= V; i++)
			arr[i] = i;

		Edge[] edge = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(sc.readLine().trim());

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			edge[i] = new Edge(s, e, c);
		}

		Arrays.sort(edge, new Comparator<Edge>() {
			public int compare(Edge e1, Edge e2) {
				return e1.c - e2.c;
			}
		});

		int an = 0;
		for (int i = 0; i < E; i++) {
			int p = find(edge[i].s);
			int q = find(edge[i].e);

			if (p == q)
				continue;
			arr[q] = p;
			an += edge[i].c;
		}

		System.out.println(an);
	}

	private static int find(int n) {
		if (arr[n] == n)
			return n;
		return arr[n] = find(arr[n]);
	}

}

class Edge {
	int s;
	int e;
	int c;

	Edge(int s, int e, int c) {
		this.s = s;
		this.e = e;
		this.c = c;
	}
}