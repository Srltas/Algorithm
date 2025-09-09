import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static final int MAX = 60;
    static final int[][] attack = {
        {9,3,1}, {9,1,3},
        {3,9,1}, {3,1,9},
        {1,9,3}, {1,3,9}
    };
    static int[][][] DP = new int[MAX + 1][MAX + 1][MAX + 1];

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());

            int[] hp = new int[3];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                hp[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(hp);
            reverse(hp);

            for (int i = 0; i <= MAX; i++) {
                for (int j = 0; j <= MAX; j++) {
                    Arrays.fill(DP[i][j], -1);
                }
            }

            int ans = bfs(hp[0], hp[1], hp[2]);
            System.out.println(ans);
        }
    }

    static int bfs(int a, int b, int c) {
        Queue<State> q = new ArrayDeque<>();
        DP[a][b][c] = 0;
        q.offer(new State(a, b, c));

        while (!q.isEmpty()) {
            State s = q.poll();
            int d = DP[s.a][s.b][s.c];
            if (s.a == 0 && s.b == 0 && s.c == 0) return d;

            for (int[] att : attack) {
                int na = Math.max(0, s.a - att[0]);
                int nb = Math.max(0, s.b - att[1]);
                int nc = Math.max(0, s.c - att[2]);
                int[] arr = {na, nb, nc};
                Arrays.sort(arr);

                na = arr[2];
                nb = arr[1];
                nc = arr[0];
                if (DP[na][nb][nc] != -1) continue;
                DP[na][nb][nc] = d + 1;
                q.offer(new State(na, nb, nc));
            }
        }
        return -1;
    }

    static void reverse(int[] x) {
        for (int i = 0, j = x.length - 1; i < j; i++, j--) {
            int t = x[i];
            x[i] = x[j];
            x[j] = t;
        }
    }

    static class State {
        int a, b, c;

        public State(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
