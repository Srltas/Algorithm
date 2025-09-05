import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};
    static final int[] dx2 = {0, 1};
    static final int[] dy2 = {1, 0};

    static int N, M;
    static int[][] map;
    static int[][] newMap;
    static boolean[][] visited;

    static List<Node>[] islands = new ArrayList[7];
    static List<BridgeEdge> bridges = new ArrayList<>();
    static int[] parent;
    static int[] rank;
    static int total, used;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            for (int i = 1; i < 7; i++) {
                islands[i] = new ArrayList<>();
            }

            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new boolean[N][M];
            newMap = new int[N][M];

            int islandNum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        checkIsland(i, j, ++islandNum);
                    }
                }
            }

            createBridge();
            findShortBridge(islandNum);

            System.out.println(used != islandNum - 1 ? -1 : total);
        }
    }

    static void findShortBridge(int islandCount) {
        rank = new int[islandCount + 1];
        parent = new int[islandCount + 1];
        for (int i = 1; i <= islandCount; i++) {
            parent[i] = i;
        }

        Collections.sort(bridges);

        for (BridgeEdge bridge : bridges) {
            int ps = find(bridge.s);
            int pe = find(bridge.e);
            if (ps == pe) continue;
            union(ps, pe);
            total += bridge.l;
            if (++used == islandCount - 1) break;
        }
    }

    static void createBridge() {
        for (List<Node> island : islands) {
            if (island == null || island.isEmpty()) continue;
            for (Node node : island) {
                int self = newMap[node.x][node.y];
                for (int i = 0; i < 2; i++) {
                    int nx = node.x + dx2[i];
                    int ny = node.y + dy2[i];

                    int length = 0;
                    while (checkRange(nx, ny) && newMap[nx][ny] == 0) {
                        length++;
                        nx += dx2[i];
                        ny += dy2[i];
                    }

                    if (!checkRange(nx, ny)) continue;

                    int other = newMap[nx][ny];
                    if (other == self) continue;
                    if (other != 0 && length >= 2) {
                        bridges.add(new BridgeEdge(self, other, length));
                    }
                }
            }
        }
    }

    static void checkIsland(int x, int y, int num) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x,y));
        visited[x][y] = true;
        newMap[x][y] = num;
        if (checkBeach(x, y))islands[num].add(new Node(x,y));

        while (!q.isEmpty()) {
            Node n = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if (checkRange(nx, ny) && !visited[nx][ny] && map[nx][ny] != 0) {
                    visited[nx][ny] = true;
                    Node node = new Node(nx, ny);
                    q.add(node);
                    newMap[nx][ny] = num;
                    if (checkBeach(nx, ny)) islands[num].add(node);
                }
            }
        }
    }

    static boolean checkBeach(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (checkRange(nx, ny) && map[nx][ny] == 0) {
                return true;
            }
        }
        return false;
    }

    static void union(int x, int y) {
        if (x == y) return;
        if (rank[x] > rank[y]) {
            parent[y] = x;
        } else if (rank[x] < rank[y]) {
            parent[x] = y;
        } else {
            parent[y] = x;
            rank[x]++;
        }
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static class BridgeEdge implements Comparable<BridgeEdge> {
        int s, e, l;
        public BridgeEdge(int s, int e, int l) { this.s = s; this.e = e; this.l = l; }

        @Override
        public int compareTo(BridgeEdge o) {
            return Integer.compare(this.l, o.l);
        }
    }

    static class Node {
        int x, y;
        public Node(int x, int y) { this.x = x; this.y = y; }
    }
}
