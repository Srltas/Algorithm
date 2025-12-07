import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int K = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= K; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int N = Integer.parseInt(st.nextToken());
                int[] scores = new int[N];
                for (int j = 0; j < N; j++) {
                    scores[j] = Integer.parseInt(st.nextToken());
                }

                Arrays.sort(scores);

                int min = scores[0];
                int max = scores[N - 1];

                int largestGap = 0;
                for (int j = 0; j < N - 1; j++) {
                    int gap = scores[j + 1] - scores[j];
                    if (gap > largestGap) {
                        largestGap = gap;
                    }
                }

                sb.append("Class ").append(i).append("\n");
                sb.append("Max ").append(max)
                    .append(", Min ").append(min)
                    .append(", Largest gap ").append(largestGap)
                    .append("\n");
            }
            System.out.print(sb);
        }
    }
}
