import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    static int N, M, D;
    static int[][] map;
    static int maxKill = 0;
    static List<Integer> archerCols = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            placeArchers(0);
            System.out.println(maxKill);
        }
    }

    static void placeArchers(int startCol) {
        if (archerCols.size() == 3) {
            simulateGame();
            return;
        }

        for (int i = startCol; i < M; i++) {
            archerCols.add(i);
            placeArchers(i + 1);
            archerCols.remove(archerCols.size() - 1);
        }
    }

    static void simulateGame() {
        int killCount = 0;
        Set<Point> killedTarget = new HashSet<>();

        for (int turn = 0; turn < N; turn++) {
            Set<Point> targetsThisTurn = findTarget(turn, killedTarget);

            for (Point target : targetsThisTurn) {
                if (killedTarget.add(target)) {
                    killCount++;
                }
            }
        }
        maxKill = Math.max(maxKill, killCount);
    }

    static Set<Point> findTarget(int turn, Set<Point> killTarget) {
        Set<Point> targets = new HashSet<>();

        for (int archerCol : archerCols) {
            int minDist = D + 1;
            Point bestTarget = null;

            for (int r = 0; r < N - turn; r++) {
                for (int c = 0; c < M; c++) {
                    if (map[r][c] == 1 && !killTarget.contains(new Point(r, c))) {
                        int row = r + turn;
                        int dist = Math.abs(N - row) + Math.abs(archerCol - c);
                        if (dist > D) continue;
                        if (dist < minDist) {
                            minDist = dist;
                            bestTarget = new Point(r, c);
                        } else if (dist == minDist) {
                            if (c < bestTarget.c) {
                                bestTarget = new Point(r, c);
                            }
                        }
                    }
                }
            }
            if (bestTarget != null) {
                targets.add(bestTarget);
            }
        }
        return targets;
    }
}
