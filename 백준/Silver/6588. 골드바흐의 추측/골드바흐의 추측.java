import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int MAX_NUM = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        boolean[] isPrime = new boolean[MAX_NUM + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= MAX_NUM; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j <= MAX_NUM; j += i) {
                isPrime[j] = false;
            }
        }

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            boolean found = false;
            for (int i = 3; i <= N; i++) {
                if (isPrime[i] && isPrime[N - i]) {
                    sb.append(N)
                        .append(" = ")
                        .append(i)
                        .append(" + ")
                        .append(N - i)
                        .append('\n');
                    found = true;
                    break;
                }
            }
            if (!found) sb.append("Goldbach's conjecture is wrong.\n");
        }
        System.out.println(sb);
    }
}
