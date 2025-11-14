import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] bridge = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                bridge[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int[] dist = new int[N + 1];
            Arrays.fill(dist, -1);

            Queue<Integer> q = new ArrayDeque<>();
            q.offer(a);
            dist[a] = 0;

            while (!q.isEmpty()) {
                int current = q.poll();

                if (current == b) {
                    System.out.println(dist[b]);
                    return;
                }

                int step = bridge[current];

                for (int i = 1; ; i++) {
                    int next = current + (step * i);
                    if (next > N) break;
                    if (dist[next] == -1) {
                        dist[next] = dist[current] + 1;
                        q.offer(next);
                    }
                }

                for (int i = 1; ; i++) {
                    int next = current - (step * i);
                    if (next < 1) break;
                    if (dist[next] == -1) {
                        dist[next] = dist[current] + 1;
                        q.offer(next);
                    }
                }
            }
            System.out.println(dist[b]);
        }
    }
}
