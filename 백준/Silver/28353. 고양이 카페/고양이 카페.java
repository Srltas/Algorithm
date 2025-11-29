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
            int K = Integer.parseInt(st.nextToken());

            int[] array = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                array[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(array);

            int left = 0;
            int right = N - 1;
            int count = 0;
            while (left < right) {
                if (array[left] + array[right] <= K) {
                    left++;
                    right--;
                    count++;
                } else {
                    right--;
                }
            }
            System.out.println(count);
        }
    }
}
