import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int gcd = GCD(A, B);
        int lcm = LCM(A, B, gcd);
        StringBuilder sb = new StringBuilder();
        sb.append(gcd).append('\n').append(lcm);
        System.out.println(sb);
    }

    static int LCM(int a, int b, int gcd) {
        return (a / gcd) * b;
    }

    static int GCD(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
