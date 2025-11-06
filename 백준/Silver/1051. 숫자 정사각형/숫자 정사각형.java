import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] board = new int[N][M];
            for (int r = 0; r < N; r++) {
                char[] line = br.readLine().toCharArray();
                for (int c = 0; c < M; c++) {
                    board[r][c] = line[c] - '0';
                }
            }
            int length = Math.min(N, M);

            for (int k = length; k > 0; k--) {
                for (int r = 0; r <= N - k; r++) {
                    for (int c = 0; c <= M - k; c++) {
                        int point = board[r][c];
                        if (point == board[r][c + k - 1]
                            && point == board[r + k - 1][c]
                            && point == board[r + k - 1][c + k - 1]) {
                            System.out.println(k * k);
                            return;
                        }
                    }
                }
            }
        }
    }
}
