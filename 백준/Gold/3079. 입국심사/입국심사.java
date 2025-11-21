import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            long maxTime = 0;
            int[] airport = new int[N];
            for (int i = 0; i < N; i++) {
                airport[i] = Integer.parseInt(br.readLine());
                maxTime = Math.max(maxTime, airport[i]);
            }

            long left = 0;
            long right = maxTime * M;
            long minTime = maxTime;
            while (left <= right) {
                long mid = (left + right) / 2;
                long totalCount = 0;
                for (int a : airport) {
                    totalCount += mid / a;
                    if (totalCount >= M) break;
                }
                if (M <= totalCount) {
                    right = mid - 1;
                    minTime = mid;
                }
                else if (M > totalCount) {
                    left = mid + 1;
                }
            }
            System.out.println(minTime);
        }
    }
}
