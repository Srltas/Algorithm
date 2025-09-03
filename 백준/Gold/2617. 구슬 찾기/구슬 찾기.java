import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            boolean[][] reach = new boolean[N + 1][N + 1];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int heavy = Integer.parseInt(st.nextToken());
                int light = Integer.parseInt(st.nextToken());
                reach[heavy][light] = true;
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (reach[i][j]) continue;
                        if (reach[i][k] && reach[k][j]) reach[i][j] = true;
                    }
                }
            }

            int mid = (N + 1) / 2;
            int result = 0;
            for (int i = 1; i <= N; i++) {
                int lightCount = 0;
                int heavyCount = 0;
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    if (reach[i][j]) lightCount++;
                    if (reach[j][i]) heavyCount++;
                }
                if (lightCount >= mid || heavyCount >= mid) result++;
            }

            System.out.println(result);
        }
    }
}
