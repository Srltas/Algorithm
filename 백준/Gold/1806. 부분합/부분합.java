import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int S  =Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0 ; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int start = 0;
            int currSum = 0;
            int currLength;
            int minLength = Integer.MAX_VALUE;
            for (int end = 0; end < N; end++) {
                currSum += arr[end];
                while (currSum >= S) {
                    currLength = end - start + 1;
                    minLength = Math.min(minLength, currLength);
                    currSum -= arr[start];
                    start++;
                }
            }

            System.out.println(minLength == Integer.MAX_VALUE ? 0 : minLength);
        }
    }
}
