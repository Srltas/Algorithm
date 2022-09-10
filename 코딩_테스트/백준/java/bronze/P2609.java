package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2609 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int gcd = 0;
        if (A > B) {
            gcd = GCD(A, B);           
        } else {
            gcd = GCD(B, A);
        }
        int lcm = A * B / gcd;

        System.out.println(gcd);
        System.out.println(lcm);
    }

    private static int GCD(int A, int B) {
        if (A % B == 0) {
            return B;
        }

        return GCD(B, A % B);
    }
}
