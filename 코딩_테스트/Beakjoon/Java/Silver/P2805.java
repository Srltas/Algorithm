package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long max = 0;
        long[] A = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());

            if (A[i] > max) {
                max = A[i];
            }
        }

        max++;

        long min = 0;
        long mid = 0;
        while (min < max) {
            mid = (min + max) / 2;
            
            long tree = 0;
            for (int i = 0; i < N; i++) {
                if (A[i] - mid > 0) {
                    tree += (A[i] - mid);
                }
            }

            if (tree < M) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(min - 1);
    }
}
