import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int L = Integer.parseInt(br.readLine());
            int[] S = new int[L];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < L; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }
            int n = Integer.parseInt(br.readLine());
            Arrays.sort(S);
            for (int num : S) {
                if (num == n) {
                    System.out.println(0);
                    return;
                }
            }

            int start = 0;
            int end = 0;
            for (int num : S) {
                if (num < n) {
                    start = num;
                } else if (num > n) {
                    end = num;
                    break;
                }
            }

            int rangeStart = start + 1;
            int rangeEnd = end - 1;
            int count = (n - rangeStart + 1) * (rangeEnd - n + 1) - 1;
            System.out.println(count);
        }
    }
}
