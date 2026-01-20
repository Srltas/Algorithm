import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX = 100001;
    private static int[] dist = new int[MAX];
    private static int[] parent = new int[MAX];
    private static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
            System.out.println(N);
            return;
        }

        BFS();
        System.out.println(dist[K] - 1);

        Stack<Integer> path = new Stack<>();
        path.push(K);

        int index = K;
        while (index != N) {
            path.push(parent[index]);
            index = parent[index];
        }

        StringBuilder sb = new StringBuilder();
        while (!path.isEmpty()) {
            sb.append(path.pop()).append(" ");
        }
        System.out.println(sb);
    }

    private static void BFS() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        dist[N] = 1;

        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == K) return;
            int[] nextMoves = { curr - 1, curr + 1, 2 * curr };

            for (int next : nextMoves) {
                if (next < 0 || next >= MAX) continue;
                if (dist[next] != 0) continue;
                q.offer(next);
                dist[next] = dist[curr] + 1;
                parent[next] = curr;
            }
        }
    }
}
