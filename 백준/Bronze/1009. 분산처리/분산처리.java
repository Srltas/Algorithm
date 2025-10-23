import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            int T = Integer.parseInt(br.readLine());
            for (int t = 0; t < T; t++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                long b = Long.parseLong(st.nextToken());
                sb.append(mod(a, b)).append('\n');
            }
            System.out.println(sb);
        }
    }

    static int mod(int a, long b) {
        int base = a % 10;
        int res = 1 % 10;
        int cur = base;
        long exp = b;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * cur) % 10;
            }
            cur = (cur * cur) % 10;
            exp >>= 1;
        }
        return res == 0 ? 10 : res;
    }
}
