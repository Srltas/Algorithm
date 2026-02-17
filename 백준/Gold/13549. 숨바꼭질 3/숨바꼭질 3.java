import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static boolean[] visited = new boolean[100001];
    static int[] time = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
            return;
        }
        bfs();
    }

    static void bfs() {
        Deque<Integer> q = new ArrayDeque<>();
        q.add(N);
        visited[N] = true;
        time[N] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            if (now == K) {
                System.out.println(time[now]);
                return;
            }

            int next = now * 2;
            if (next <= 100000 && !visited[next]) {
                visited[next] = true;
                time[next] = time[now];
                q.addFirst(next);
            }

            int[] walk = {now - 1, now + 1};
            for (int n : walk) {
                if (n >= 0 && n <= 100000 && !visited[n]) {
                    visited[n] = true;
                    time[n] = time[now] + 1;
                    q.addLast(n);
                }
            }
        }
    }
}
