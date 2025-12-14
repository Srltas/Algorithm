import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());
            while (T-- > 0) {
                int[] scores = new int[5];
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int i = 0; i < 5; i++) {
                    scores[i] = Integer.parseInt(st.nextToken());
                }

                Arrays.sort(scores);
                if (scores[4] - scores[1] >= 4) {
                    System.out.println("KIN");
                    continue;
                }
                int score = scores[1] + scores[2] + scores[3];
                System.out.println(score);
            }
        }
    }
}
