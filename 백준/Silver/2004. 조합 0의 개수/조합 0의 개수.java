import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        long count5 = findFive(n) - (findFive(m) + findFive(n - m));
        long count2 = findTwo(n) - (findTwo(m) + findTwo(n - m));

        // n! - (m! + (n - m)!)
        System.out.println(Math.min(count5, count2));
    }

    static long findFive(long n) {
        long count = 0;
        while (n >= 5) {
            count += n / 5;
            n /= 5;

        }
        return count;
    }

    static long findTwo(long n) {
        long count = 0;
        while (n >= 2) {
            count += n / 2;
            n /= 2;
        }
        return count;
    }
}
