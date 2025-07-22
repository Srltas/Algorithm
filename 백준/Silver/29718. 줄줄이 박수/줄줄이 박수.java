import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] array = new int[M];
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                array[j] += Integer.parseInt(str[j]);
            }
        }

        int A = Integer.parseInt(br.readLine());

        int max = 0;
        for (int i = 0; i < A; i++) {
            max += array[i];
        }

        int num = max;
        for (int i = A; i < M; i++) {
            num += array[i] - array[i - A];
            max = Math.max(max, num);
        }
        System.out.println(max);
    }
}