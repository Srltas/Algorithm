import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            int[] straws = new int[N];

            for (int i = 0; i < N; i++) {
                straws[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(straws);

            for (int i = N - 1; i >= 2; i--) {
                if (straws[i - 1] + straws[i - 2] > straws[i]) {
                    System.out.println(straws[i - 1] + straws[i - 2] + straws[i]);
                    return;
                }
            }

            System.out.println(-1);
        }
    }
}
