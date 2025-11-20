import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());
            while (T-- > 0) {
                int N = Integer.parseInt(br.readLine());
                graph = new int[N + 1];
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int i = 1; i <= N; i++) {
                    graph[i] = Integer.parseInt(st.nextToken());
                }

                visited = new boolean[N + 1];
                int result = 0;
                for (int i = 1; i <= N; i++) {
                    if (!visited[i]) {
                        dfs(i);
                        result++;
                    }
                }
                System.out.println(result);
            }
        }
    }

    static void dfs(int node) {
        if (visited[node]) return;
        visited[node] = true;
        dfs(graph[node]);
    }
}
