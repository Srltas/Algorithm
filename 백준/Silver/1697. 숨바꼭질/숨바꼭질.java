import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static final int MAX_VALUE = 100_001;
  static final int MIN_VALUE = -1;
  static int N, K;
  static boolean[] visited = new boolean[MAX_VALUE];

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());

      System.out.println(bfs());
    }
  }

  static int bfs() {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(N);

    int depth = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int n = queue.poll();

        if (n == K) {
          return depth;
        }

        visited[n] = true;
        if (n + 1 < MAX_VALUE && !visited[n + 1]) {
          queue.offer(n + 1);
        }

        if (n - 1 > MIN_VALUE && !visited[n - 1]) {
          queue.offer(n - 1);
        }

        if (n * 2 < MAX_VALUE && !visited[n * 2]) {
          queue.offer(n * 2);
        }
      }
      depth++;
    }
    return -1;
  }
}
