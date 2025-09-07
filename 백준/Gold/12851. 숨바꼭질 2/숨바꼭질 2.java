import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MAX = 100_000;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            if (N > K) {
                System.out.println(N - K);
                System.out.println(1);
                return;
            }

            int[] dist = new int[MAX + 1];
            int[] ways = new int[MAX + 1];
            Arrays.fill(dist, -1);

            Queue<Integer> q = new ArrayDeque<>();
            q.offer(N);
            dist[N] = 0;
            ways[N] = 1;

            while (!q.isEmpty()) {
                int c = q.poll();
                int nextDist = dist[c] + 1;
                if (dist[K] != -1 && dist[nextDist] > dist[K]) break;

                int[] next = {c - 1, c + 1, 2 * c};
                for (int n : next) {
                    if (n < 0 || n > MAX) continue;
                    if (dist[n] == -1) {
                        dist[n] = nextDist;
                        ways[n] = ways[c];
                        q.offer(n);
                    } else if (dist[n] == nextDist) {
                        ways[n] += ways[c];
                    }
                }
            }

            System.out.println(dist[K]);
            System.out.println(ways[K]);
        }
    }
}
