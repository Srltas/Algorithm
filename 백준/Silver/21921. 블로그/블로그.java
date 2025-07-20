import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());

            int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int sum = 0;
            for (int i = 0; i < X; i++) {
                sum += array[i];
            }

            int max = sum;
            int count = 1;

            for (int i = X; i< N; i++) {
                sum = sum - array[i - X] + array[i];

                if (max == sum) {
                    count++;
                } else if (sum > max) {
                    max = sum;
                    count = 1;
                }
            }

            if (max == 0) {
                System.out.println("SAD");
            } else {
                System.out.println(max);
                System.out.println(count);
            }
        }
    }
}