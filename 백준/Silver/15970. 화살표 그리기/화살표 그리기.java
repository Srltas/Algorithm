import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            List<Integer>[] pointsByColor = new ArrayList[N + 1];
            for (int i = 0; i <= N; i++) {
                pointsByColor[i] = new ArrayList<>();
            }
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int location = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());
                pointsByColor[color].add(location);
            }

            int totalSum = 0;

            for (int i = 1; i <= N; i++) {
                if (pointsByColor[i].isEmpty()) continue;
                Collections.sort(pointsByColor[i]);
                List<Integer> list = pointsByColor[i];
                for (int j = 0; j < list.size(); j++) {
                    int currentPos = list.get(j);
                    int minDistance = Integer.MAX_VALUE;
                    if (j > 0) {
                        int leftDist = currentPos - list.get(j - 1);
                        minDistance = Math.min(minDistance, leftDist);
                    }
                    if (j < list.size() - 1) {
                        int rightDist = list.get(j + 1) - currentPos;
                        minDistance = Math.min(minDistance, rightDist);
                    }
                    totalSum += minDistance;
                }
            }
            System.out.println(totalSum);
        }
    }
}
