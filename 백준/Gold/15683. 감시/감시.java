import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int[][][] dire = {
        null,
        { {0}, {1}, {2}, {3} },
        { {0, 2}, {1, 3}},
        { {0, 1}, {1, 2}, {2, 3}, {3, 0} },
        { {0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {0, 2, 3} },
        { {0, 1, 2, 3} }
    };
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

    static int N, M;
    static List<CCTV> cctvList = new ArrayList<>();
    static int minCount;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            minCount = N * M;

            int[][] room = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int n = Integer.parseInt(st.nextToken());
                    room[i][j] = n;
                    if (n == 0 || n == 6) continue;
                    cctvList.add(new CCTV(i, j, n));
                }
            }

            solve(0, room);
            System.out.println(minCount);
        }
    }

    static void solve(int index, int[][] currentRoom) {
        if (index == cctvList.size()) {
            findZero(currentRoom);
            return;
        }

        CCTV cur = cctvList.get(index);
        int type = cur.t;
        int r = cur.r;
        int c = cur.c;

        int[][] possibleDir = dire[type];
        for (int[] d : possibleDir) {
            int[][] tempRoom = copyRoom(currentRoom);
            drawVision(tempRoom, r, c, d);
            solve(index + 1, tempRoom);
        }
    }

    static void drawVision(int[][] tempRoom, int r, int c, int[] dirList) {
        for (int d : dirList) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            while (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                if (tempRoom[nr][nc] == 6) {
                    break;
                }
                if (tempRoom[nr][nc] == 0) {
                    tempRoom[nr][nc] = '#';
                }
                nr += dr[d];
                nc += dc[d];
            }
        }
    }

    static int[][] copyRoom(int[][] room) {
        int[][] r = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                r[i][j] = room[i][j];
            }
        }
        return r;
    }

    static void findZero(int[][] room) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (room[i][j] == 0) {
                    count++;
                }
            }
        }
        minCount = Math.min(minCount, count);
    }

    static class CCTV {
        int r, c, t;
        public CCTV(int r, int c, int t) { this.r = r; this.c = c; this.t = t; }
    }
}
