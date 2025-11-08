import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};

    static int N, K;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N][N];
            List<Node> virus = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    arr[i][j] = v;
                    if (v != 0) virus.add(new Node(v, i, j));
                }
            }

            virus.sort(Comparator.comparingInt(a -> a.n));

            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            Queue<Node> q = new ArrayDeque<>();
            for (Node v : virus) q.offer(v);

            int time = 0;
            while(!q.isEmpty() && time < S) {
                int size = q.size();
                for (int s = 0; s < size; s++) {
                    Node node = q.poll();
                    for (int i = 0; i < 4; i++) {
                        int nx = node.x + dx[i];
                        int ny = node.y + dy[i];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] != 0) continue;
                        arr[nx][ny] = node.n;
                        q.offer(new Node(node.n, nx, ny));
                    }
                }
                time++;
            }
            System.out.println(arr[X - 1][Y - 1]);
        }
    }

    static class Node {
        int n, x, y;

        public Node(int n, int x, int y) {
            this.n = n;
            this.x = x;
            this.y = y;
        }
    }
}
