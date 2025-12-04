import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            Integer[] tips = new Integer[N];
            for (int i = 0; i < N; i++) {
                tips[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(tips, Collections.reverseOrder());

            long total = 0;
            for (int i = 0; i < N; i++) {
                int current = tips[i] - i;
                if (current > 0) {
                    total += current;
                }
            }
            System.out.println(total);
        }
    }
}
