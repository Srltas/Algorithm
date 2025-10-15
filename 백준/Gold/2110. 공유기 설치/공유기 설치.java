import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(arr);

            int low = 1;
            int high = arr[N - 1] - arr[0];
            int answer = 0;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int count = 1;
                int lastPosition = arr[0];
                for (int i = 1; i < N; i++) {
                    if (arr[i] - lastPosition >= mid) {
                        count++;
                        if (count == C) break;
                        lastPosition = arr[i];
                    }
                }

                if (count >= C) {
                    low = mid + 1;
                    answer = mid;
                } else {
                    high = mid - 1;
                }
            }
            System.out.println(answer);
        }
    }
}
