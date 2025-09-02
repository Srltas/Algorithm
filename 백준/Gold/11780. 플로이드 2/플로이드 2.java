import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 10_000_001;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            int[][] city = new int[N + 1][N + 1];
            for (int i = 1; i < N + 1; i++) {
                Arrays.fill(city[i], INF);
                city[i][i] = 0;
            }
            int[][] path = new int[N + 1][N + 1];

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                if (city[s][e] > c) {
                    city[s][e] = c;
                    path[s][e] = e;
                }
            }

            for (int k = 1; k < N + 1; k++) {
                for (int i = 1; i < N + 1; i++) {
                    if (city[i][k] == INF) continue;
                    for (int j = 1; j < N + 1; j++) {
                        if (city[k][j] == INF) continue;
                        int n = city[i][k] + city[k][j];
                        if (city[i][j] > n) {
                            city[i][j] = n;
                            path[i][j] = path[i][k];
                        }
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    sb.append(city[i][j] == INF ? 0 : city[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.print(sb);

            StringBuilder sb2 = new StringBuilder();
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (i == j || path[i][j] == 0) {
                        sb2.append(0);
                    } else {
                        List<Integer> route = new ArrayList<>();
                        int cur = i;
                        route.add(cur);
                        while (cur != j) {
                            cur = path[cur][j];
                            route.add(cur);
                        }
                        if (route.isEmpty()) {
                            sb2.append(0);
                        } else {
                            sb2.append(route.size()).append(" ");
                            for (int v : route) {
                                sb2.append(v).append(" ");
                            }
                        }
                    }
                    sb2.append("\n");
                }
            }
            System.out.println(sb2);
        }
    }
}
