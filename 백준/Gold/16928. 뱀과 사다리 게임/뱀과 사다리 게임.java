import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] board = new int[101];
    static int[] dist = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            board[s] = e;
        }

        Arrays.fill(dist, -1);
        System.out.println(move());
    }

    static int move() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        dist[1] = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == 100) return dist[100];
            for (int n = 1; n <= 6; n++) {
                int next = curr + n;
                if (next > 100) continue;
                int dest = board[next] == 0 ? next : board[next];
                if (dist[dest] == -1) {
                    dist[dest] = dist[curr] + 1;
                    q.offer(dest);
                }
            }
        }
        return -1;
    }
}
