import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};

    static boolean[][] board = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            while (N-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int g = Integer.parseInt(st.nextToken());

                List<Integer> dir = new ArrayList<>();
                dir.add(d);

                for (int gen = 0; gen < g; gen++) {
                    int currSize = dir.size();
                    for (int i = currSize - 1; i >= 0; i--) {
                        int newDir = (dir.get(i) + 1) % 4;
                        dir.add(newDir);
                    }
                }

                board[x][y] = true;
                for (Integer nd : dir) {
                    x += dx[nd];
                    y += dy[nd];
                    board[x][y] = true;
                }
            }

            int count = 0;
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (board[i][j] && board[i][j+1] && board[i+1][j] && board[i+1][j+1]) count++;
                }
            }
            System.out.println(count);
        }
    }
}
