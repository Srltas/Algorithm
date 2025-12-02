import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int[][] scores = new int[8][2];

            for (int i = 0; i < 8; i++) {
                scores[i][0] = Integer.parseInt(br.readLine());
                scores[i][1] = i + 1;
            }

            Arrays.sort(scores, (o1, o2) -> o2[0] - o1[0]);

            int sum = 0;
            int[] resultIndex = new int[5];

            for (int i = 0; i < 5; i++) {
                sum += scores[i][0];
                resultIndex[i] = scores[i][1];
            }

            Arrays.sort(resultIndex);

            StringBuilder sb = new StringBuilder();
            sb.append(sum).append("\n");
            for (int index : resultIndex) {
                sb.append(index).append(" ");
            }

            System.out.println(sb);
        }
    }
}
