import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Queue<int[]> q = new ArrayDeque<>();
            Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int n = Integer.parseInt(st.nextToken());
                q.offer(new int[]{i, n});
                pq.offer(n);
            }

            int count = 0;
            while (!q.isEmpty()) {
                int[] curr = q.poll();

                if (curr[1] == pq.peek()) {
                    count++;
                    pq.poll();
                    if (curr[0] == M) {
                        System.out.println(count);
                        break;
                    }
                } else {
                    q.offer(curr);
                }
            }
        }
    }
}
