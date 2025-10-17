import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int TOP = 0, BOTTOM = 1, NORTH = 2, SOUTH = 3, EAST = 4, WEST = 5;
    static final int[] dx = {0, 0, 0, -1, 1};
    static final int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[] move = new int[K];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                move[i] = Integer.parseInt(st.nextToken());
            }

            Dice dice = new Dice();
            StringBuilder sb = new StringBuilder();
            for (int m : move) {
                int nx = x + dx[m], ny = y + dy[m];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                switch (m) {
                    case 1: dice.rollEast(); break;
                    case 2: dice.rollWest(); break;
                    case 3: dice.rollNorth(); break;
                    case 4: dice.rollSouth(); break;
                }

                x = nx; y = ny;
                if (map[x][y] == 0) {
                    map[x][y] = dice.f[BOTTOM];
                } else {
                    dice.f[BOTTOM] = map[x][y];
                    map[x][y] = 0;
                }
                sb.append(dice.f[TOP]).append('\n');
            }
            System.out.println(sb);
        }
    }

    static class Dice {
        int[] f = new int[6];

        void rollEast() {
            int t = f[TOP], b = f[BOTTOM], e = f[EAST], w = f[WEST];
            f[TOP] = w; f[BOTTOM] = e; f[EAST] = t; f[WEST] = b;
        }

        void rollWest() {
            int t = f[TOP], b = f[BOTTOM], e = f[EAST], w = f[WEST];
            f[TOP] = e; f[BOTTOM] = w; f[EAST] = b; f[WEST] = t;
        }

        void rollNorth() {
            int t = f[TOP], b = f[BOTTOM], n = f[NORTH], s = f[SOUTH];
            f[TOP] = s; f[BOTTOM] = n; f[NORTH] = t; f[SOUTH] = b;
        }

        void rollSouth() {
            int t = f[TOP], b = f[BOTTOM], n = f[NORTH], s = f[SOUTH];
            f[TOP] = n; f[BOTTOM] = s; f[NORTH] = b; f[SOUTH] = t;
        }
    }
}
