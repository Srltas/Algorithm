import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dp = new int[10001];
        Arrays.fill(dp, 1);
        for (int i = 2; i <= 10000; i++) dp[i] = dp[i] + dp[i - 2];
        for (int i = 3; i <= 10000; i++) dp[i] = dp[i] + dp[i - 3];

        int T = parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            sb.append(dp[parseInt(br.readLine())]).append('\n');
        }
        System.out.println(sb);
    }
}
