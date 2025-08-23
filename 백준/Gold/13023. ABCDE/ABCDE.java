import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static List<Integer>[] friends;
    static boolean[] visited;
    static boolean result;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            friends = new ArrayList[N];
            visited = new boolean[N];
            for (int i = 0; i < N; i++) {
                friends[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                friends[a].add(b);
                friends[b].add(a);
            }

            for (int i = 0; i < N; i++) {
                if (result) break;
                dfs(0, i);
            }

            System.out.println(result ? 1 : 0);
        }
    }

    static void dfs(int depth, int node){
        if (result) return;
        if (depth == 4) {
            result = true;
            return;
        }

        visited[node] = true;
        for (int n : friends[node]) {
            if (!visited[n]) {
                dfs(depth + 1, n);
                if (result) {
                    visited[n] = false;
                    return;
                }
            }
        }
        visited[node] = false;
    }
}
