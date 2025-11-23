import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Integer>[] graph;
    static int[] count;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }

            count = new int[N + 1];
            dfs(1);

            int result = 0;
            for (int i = 2; i <= N; i++) {
                if (graph[i].size() == 1) {
                    result += count[i];
                }
            }

            System.out.println(result % 2 == 0 ? "No" : "Yes");
        }
    }

    static void dfs(int current) {
        for (int next : graph[current]) {
            if (count[next] != 0) continue;
            count[next] = count[current] + 1;
            dfs(next);
        }
    }
}
