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
    static boolean found;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            friends = new ArrayList[N];
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

            visited = new boolean[N];
            for (int i = 0; i < N; i++) {
                dfs(0, i);
            }

            System.out.println(found ? 1 : 0);
        }
    }

    static void dfs(int depth, int friend) {
        if (found) return;
        if (depth == 4) {
            found = true;
            return;
        }

        visited[friend] = true;
        for (int f :friends[friend]) {
            if (visited[f]) continue;
            dfs(depth + 1, f);
            if (found) return;
        }
        visited[friend] = false;
    }
}
