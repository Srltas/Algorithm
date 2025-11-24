import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, R;
    static List<Node>[] graph;
    static int[] length;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph[a].add(new Node(b, d));
                graph[b].add(new Node(a, d));
            }

            length = new int[N + 1];
            Arrays.fill(length, -1);
            length[R] = 0;
            dfs(new Node(R, 0));

            visited = new boolean[N + 1];
            visited[R] = true;
            int gigaNodeIndex = findGiga(R);

            int pillarLength = length[gigaNodeIndex];
            int maxDistanceData = 0;
            for (int i = 1; i <= N; i++) {
                if (length[i] > maxDistanceData) {
                    maxDistanceData = length[i];
                }
            }

            int maxBranchLength = maxDistanceData - pillarLength;
            System.out.println(pillarLength + " " + maxBranchLength);
        }
    }

    static int findGiga(int startNode) {
        int curr = startNode;

        while (true) {
            if ((curr == R && graph[curr].size() >= 2) || (curr != R && graph[curr].size() > 2)) {
                return curr;
            }
            boolean hasNext = false;
            for (Node next : graph[curr]) {
                if (!visited[next.v]) {
                    visited[next.v] = true;
                    curr = next.v;
                    hasNext = true;
                    break;
                }
            }
            if (!hasNext) {
                return curr;
            }
        }
    }

    static void dfs(Node current) {
        for (Node next : graph[current.v]) {
            if (length[next.v] != -1) continue;
            length[next.v] = length[current.v] + next.d;
            dfs(next);
        }
    }

    static class Node {
        int v, d;

        public Node(int v, int d) {
            this.v = v;
            this.d = d;
        }
    }
}
