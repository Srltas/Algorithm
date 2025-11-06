import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int[] result = new int[N];
            for (int i = 0; i < N; i++) {
                int height = i + 1;
                int leftCount = arr[i];
                int emptyCount = 0;

                for (int j = 0; j < N; j++) {
                    if (result[j] == 0) {
                        if (emptyCount == leftCount) {
                            result[j] = height;
                            break;
                        }
                        emptyCount++;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(result[i]).append(' ');
            }
            System.out.println(sb);
        }
    }
}
