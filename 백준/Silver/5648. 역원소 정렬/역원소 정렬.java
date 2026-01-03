import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                while (!st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
                arr[i] = reverseNum(Long.parseLong(st.nextToken()));
            }
            Arrays.sort(arr);
            StringBuilder sb = new StringBuilder();
            for (long num : arr) {
                sb.append(num).append('\n');
            }
            System.out.println(sb);
        }
    }

    private static long reverseNum(long num) {
        long reversed = 0;
        while (num > 0) {
            reversed = reversed * 10 + (num % 10);
            num /= 10;
        }
        return reversed;
    }
}
