import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            int[][] city = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    city[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    if (i == k) continue;
                    for (int j = 0; j < N; j++) {
                        if (j == k || i == j) continue;
                        if (city[i][j] > city[i][k] + city[k][j]) {
                            System.out.println(-1);
                            return;
                        }
                    }
                }
            }

            int result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    boolean shortPath = true;
                    for (int k = 0; k < N; k++) {
                        if (k == i || k == j) continue;
                        if (city[i][j] == city[i][k] + city[k][j]) {
                            shortPath = false;
                            break;
                        }
                    }
                    if (shortPath) result += city[i][j];
                }
            }
            System.out.println(result);
        }
    }
}
