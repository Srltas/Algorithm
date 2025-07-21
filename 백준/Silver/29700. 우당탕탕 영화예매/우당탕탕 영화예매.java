import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (K > M) {
            System.out.println(0);
            return;
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            int zeroCount = 0;

            for (int j = 0; j < K; j++) {
                if (row.charAt(j) == '0') {
                    zeroCount++;
                }
            }
            if (zeroCount == K) {
                count++;
            }
            for (int j = K; j < M; j++) {
                if (row.charAt(j - K) == '0') {
                    zeroCount--;
                }
                if (row.charAt(j) == '0') {
                    zeroCount++;
                }
                if (zeroCount == K) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}