import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            Jewelry[] jewelries = new Jewelry[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int m = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                jewelries[i] = new Jewelry(m, v);
            }

            int[] bags = new int[K];
            for (int i = 0; i < K; i++) {
                bags[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(jewelries, (a ,b) -> {
                if (a.m != b.m) return Integer.compare(a.m, b.m);
                return Integer.compare(a.v, b.v);
            });

            Arrays.sort(bags);

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            long total = 0;
            int j = 0;
            for (int c : bags) {
                while (j < N && jewelries[j].m <= c) {
                    maxHeap.offer(jewelries[j].v);
                    j++;
                }
                if (!maxHeap.isEmpty()) {
                    total += maxHeap.poll();
                }
            }
            System.out.println(total);
        }
    }

    static class Jewelry {
        int m, v;

        public Jewelry(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }
}
