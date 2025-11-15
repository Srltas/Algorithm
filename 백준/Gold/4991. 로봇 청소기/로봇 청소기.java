import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final char DIRTY = '*';
    static final char FURNITURE = 'x';
    static final char ROBOT = 'o';

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static int W, H;
    static char[][] room;
    static int[][] dist;
    static int dirtyCount;
    static int minMoves;
    static boolean[] visitedPerm;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                W = Integer.parseInt(st.nextToken());
                H = Integer.parseInt(st.nextToken());
                if (W == 0 && H == 0) break;

                room = new char[H][W];

                dirtyCount = 0;
                int[][] point = new int[11][2];
                for (int h = 0; h < H; h++) {
                    char[] row = br.readLine().toCharArray();
                    for (int w = 0; w < W; w++) {
                        char c = row[w];
                        if (c == DIRTY) {
                            dirtyCount++;
                            point[dirtyCount] = new int[] {h ,w};
                        }
                        if (c == ROBOT) point[0] = new int[] {h, w};
                        room[h][w] = c;
                    }
                }

                int totalPoints = dirtyCount + 1;
                dist = new int[totalPoints][totalPoints];
                boolean impossible = false;

                for (int i = 0; i < totalPoints; i++) {
                    for (int j = i + 1; j < totalPoints; j++) {
                        int d = bfs(point[i][0], point[i][1], point[j][0], point[j][1]);
                        if (d == -1) {
                            impossible = true;
                            break;
                        }
                        dist[i][j] = d;
                        dist[j][i] = d;
                    }
                    if (impossible) break;
                }

                if (impossible) {
                    sb.append("-1\n");
                    continue;
                }

                minMoves = Integer.MAX_VALUE;
                visitedPerm = new boolean[dirtyCount + 1];
                findMinPath(0,0,0);
                sb.append(minMoves).append('\n');
            }
            System.out.print(sb);
        }
    }

    static void findMinPath(int currentPointIndex, int visitedCount, int currentDist) {
        if (visitedCount == dirtyCount) {
            minMoves = Math.min(minMoves, currentDist);
            return;
        }

        if (currentDist >= minMoves) {
            return;
        }

        for (int next = 1; next <= dirtyCount; next++) {
            if (!visitedPerm[next]) {
                visitedPerm[next] = true;
                findMinPath(
                    next,
                    visitedCount + 1,
                    currentDist + dist[currentPointIndex][next]);
                visitedPerm[next] = false;
            }
        }
    }

    static int bfs(int startY, int startX, int endY, int endX) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[H][W];
        q.offer(new Node(startY, startX, 0));
        visited[startY][startX] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();
            int y = curr.y;
            int x = curr.x;
            int d = curr.d;
            if (y == endY && x == endX) return d;
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (outRoom(ny, nx)
                    || visited[ny][nx]
                    || room[ny][nx] == FURNITURE) continue;
                visited[ny][nx] = true;
                q.offer(new Node(ny, nx, d + 1));
            }
        }
        return -1;
    }

    static boolean outRoom(int y, int x) {
        return y < 0 || x < 0 || y >= H || x >= W;
    }

    static class Node {
        int y, x, d;

        public Node(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }
}
