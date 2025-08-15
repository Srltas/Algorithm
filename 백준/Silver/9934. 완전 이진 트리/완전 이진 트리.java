import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static int K, N;
  static int[] in;
  static List<List<Integer>> levels;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      K = Integer.parseInt(br.readLine());
      N = (1 << K) - 1;
      in = new int[N];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) in[i] = Integer.parseInt(st.nextToken());

      levels = new ArrayList<>();
      for (int i = 0; i < K; i++) levels.add(new ArrayList<>());
    }

    dfs(0, 0, N - 1);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < K; i++) {
      for (int j = 0; j < levels.get(i).size(); j++) {
        sb.append(levels.get(i).get(j)).append(" ");
      }
      sb.append("\n");
    }
    System.out.println(sb);
  }

  static void dfs(int level, int l, int r) {
    if (l > r) return;
    int mid = (l + r) >>> 1;
    levels.get(level).add(in[mid]);
    dfs(level + 1, l, mid - 1);
    dfs(level + 1, mid + 1, r);
  }
}
