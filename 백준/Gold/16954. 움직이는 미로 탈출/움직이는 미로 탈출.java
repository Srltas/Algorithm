import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static final char WALL = '#';
    static final int SIZE = 8;
    static final char[][] board = new char[SIZE][SIZE];

    static final int[] dr = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static final int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < SIZE; i++) {
                board[i] = br.readLine().toCharArray();
            }

            Queue<Node> q = new ArrayDeque<>();
            q.offer(new Node(7, 0, 0));
            boolean[][][] visited = new boolean[SIZE][SIZE][10];
            visited[7][0][0] = true;
            while (!q.isEmpty()) {
                Node current = q.poll();
                int r = current.r;
                int c = current.c;
                int t = current.t;

                if (t >= 8) {
                    System.out.println(1);
                    return;
                }

                if (r == 0 && c == 7) {
                    System.out.println(1);
                    return;
                }

                for (int i = 0; i < 9; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    int nt = t + 1;
                    if (nr < 0 || nr >= 8 || nc < 0 || nc >= 8) continue;
                    if (nr - current.t >= 0 && board[nr - current.t][nc] == WALL) continue;
                    if (nr - nt >= 0 && board[nr - nt][nc] == WALL) continue;
                    if (visited[nr][nc][nt]) continue;
                    visited[nr][nc][nt] = true;
                    q.offer(new Node(nr, nc, nt));
                }
            }

            for (int i = 0; i < 10; i++) {
                if (visited[0][7][i]) {
                    System.out.println(1);
                    return;
                }
            }
            System.out.println(0);
        }
    }

    static class Node {
        int r, c, t;

        public Node(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }
}
