import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static final int MAX = 100_000;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      System.out.println(bfs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    }
  }

  static int bfs(int N, int K) {
    if (N >= K) return N - K;

    int[] dist = new int[MAX + 1];
    Arrays.fill(dist, -1);

    ArrayDeque<Integer> q = new ArrayDeque<>();
    q.offer(N);
    dist[N] = 0;

    while (!q.isEmpty()) {
      int x = q.poll();
      if (x == K) return dist[x];

      int[] nexts = {x - 1, x + 1, x * 2};
      for (int next : nexts) {
        if (0 <= next && next <= MAX && dist[next] == -1) {
          dist[next] = dist[x] + 1;
          if (next == K) return dist[next];
          q.offer(next);
        }
      }
    }
    return -1;
  }
}
