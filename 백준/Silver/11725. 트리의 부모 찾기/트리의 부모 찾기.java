import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  static int N;
  static List<Integer>[] tree;
  static boolean[] visited;
  static int[] result;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      N = Integer.parseInt(br.readLine());

      tree = new ArrayList[N + 1];
      for (int i = 1; i < N + 1; i++) {
        tree[i] = new ArrayList<>();
      }

      for (int i = 1; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        tree[r].add(v);
        tree[v].add(r);
      }

      visited = new boolean[N + 1];
      result = new int[N + 1];

      bfs();

      for (int i = 2; i < result.length; i++) {
        System.out.println(result[i]);
      }
    }
  }

  static void bfs() {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(1);
    visited[1] = true;

    while (!queue.isEmpty()) {
      int currentNode = queue.poll();

      for (Integer n : tree[currentNode]) {
        if (!visited[n]) {
          visited[n] = true;
          result[n] = currentNode;
          queue.add(n);
        }
      }
    }
  }
}
