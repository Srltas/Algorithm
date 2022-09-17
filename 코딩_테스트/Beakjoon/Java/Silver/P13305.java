package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P13305 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] K = new long[N-1];
        for (int i = 0; i < N - 1; i++) {
            K[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        long[] L = new long[N];
        L[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            long temp = Long.parseLong(st.nextToken());
            if (L[i - 1] < temp) {
                L[i] = L[i - 1];
            } else {
                L[i] = temp;
            }
        }

        long sum = 0;
        for (int i = 0; i < K.length; i++) {
            sum += K[i] * L[i];
        }

        System.out.println(sum);
    }
}
