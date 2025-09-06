import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] cheese;
    static boolean[][] air;
    static boolean[][] visited;
    static boolean[][] toMelt;
    static int[] dx = {0, 0, -1 ,1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            cheese = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    cheese[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new boolean[N][M];
            air = new boolean[N][M];
            toMelt = new boolean[N][M];

            int hours = 0;
            while (true) {
                for (int i = 0; i < N; i++) {
                    Arrays.fill(visited[i], false);
                    Arrays.fill(air[i], false);
                    Arrays.fill(toMelt[i], false);
                }
                air();
                boolean hasCheese = false;
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < M; y++) {
                        if (cheese[x][y] == 1) {
                            hasCheese = true;
                            int count = 0;
                            for (int i = 0; i < 4; i++) {
                                int nx = x + dx[i];
                                int ny = y + dy[i];
                                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                                if (air[nx][ny]) count++;
                            }
                            if (count >= 2) toMelt[x][y] = true;
                        }
                    }
                }
                if (!hasCheese) break;
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < M; y++) {
                        if (toMelt[x][y]) cheese[x][y] = 0;
                    }
                }
                hours++;
            }
            System.out.println(hours);
        }
    }

    static void air() {
        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node n = q.poll();
            air[n.x][n.y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && cheese[nx][ny] != 1) {
                    if (visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny));
                }
            }
        }
    }

    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
