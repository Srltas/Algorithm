import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int count = 0;
    static boolean[] col, d1, d2;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            col = new boolean[N];
            d1 = new boolean[2*N-1];
            d2 = new boolean[2*N-1];

            dfs(0);
            System.out.println(count);
        }
    }

    static void dfs(int depth) {
        if (depth == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            int k1 = depth + i;
            int k2 = depth - i + N - 1;
            if (col[i] || d1[k1] || d2[k2]) continue;
            col[i] = d1[k1] = d2[k2] = true;
            dfs(depth + 1);
            col[i] = d1[k1] = d2[k2] = false;
        }
    }
}
