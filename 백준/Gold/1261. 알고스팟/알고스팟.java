import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static final int[] DX = {0, 0, 1, -1};
    static final int[] DY = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int[][] board = new int[N][M];
            int[][] distance = new int[N][M];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    board[i][j] = line.charAt(j) - '0';
                }
                Arrays.fill(distance[i], Integer.MAX_VALUE);
            }

            Deque<int[]> dq = new ArrayDeque<>();
            dq.addFirst(new int[]{0, 0});
            distance[0][0] = 0;
            while (!dq.isEmpty()) {
                int[] cur = dq.pollFirst();
                int x = cur[0], y = cur[1];

                if (x == N - 1 && y == M - 1) {
                    System.out.println(distance[x][y]);
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = x + DX[i];
                    int ny = y + DY[i];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                    int nd = distance[x][y] + (board[nx][ny] == 1 ? 1 : 0);
                    if (nd < distance[nx][ny]) {
                        distance[nx][ny] = nd;
                        if (board[nx][ny] == 0) dq.addFirst(new int[]{nx, ny});
                        else dq.addLast(new int[]{nx, ny});
                    }
                }
            }
            System.out.println(distance[N - 1][M - 1]);
        }
    }
}
