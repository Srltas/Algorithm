import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            dfs(1, 2);
            dfs(1, 3);
            dfs(1, 5);
            dfs(1, 7);
            System.out.println(sb);
        }
    }

    static void dfs(int depth, int num) {
        if (depth == N) {
            sb.append(num).append("\n");
            return;
        }

        num *= 10;
        for (int i = 1; i < 10; i++) {
            num += i;
            if (primeCheck(num)) {
                dfs(depth + 1, num);
            }
            num -= i;
        }
    }

    static boolean primeCheck(int n) {
        if (n % 2 == 0 || n % 3 == 0) return false;
        // 6k +- 1 패턴만 검사
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}
