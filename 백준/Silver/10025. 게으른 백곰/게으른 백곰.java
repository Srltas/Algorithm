import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] array = new int[1_000_001];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int ice = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());
            array[index] = ice;
            min = Math.min(min, index);
            max = Math.max(max, index);
        }

        int sum = 0;
        for (int i = min; i <= Math.min(1_000_000, min + (K * 2)); i++) {
            sum += array[i];
        }
        int result = sum;

        for (int i = min + (K * 2) + 1; i <= max; i++) {
            sum += array[i] - array[i - (K * 2) - 1];
            result = Math.max(result, sum);
        }
        System.out.println(result);
    }
}