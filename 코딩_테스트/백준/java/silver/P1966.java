package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1966 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
            int[] a = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                q.add(n);
                a[j] = n;
            }
            
            int k = 0;
            int count = 1;
            while (k < M) {
                if (a[k] != q.peek()) {
                    k = (k + 1) % M;
                } else {
                    if (k == p) {
                        System.out.println(count);
                        break;
                    } else {
                        q.poll();
                        k = (k + 1) % M;
                        count++;
                    }
                }
            }
        }
    }
}
