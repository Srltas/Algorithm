import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            int[] height = new int[W];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < W; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }

            if (W <= 2) {
                System.out.println(0);
                return;
            }

            int[] leftMax = new int[W];
            int[] rightMax = new int[W];

            leftMax[0] = height[0];
            for (int i = 1; i < W; i++) {
                leftMax[i] = Math.max(leftMax[i - 1], height[i]);
            }

            rightMax[W - 1] = height[W - 1];
            for (int i = W - 2; i >= 0; i--) {
                rightMax[i] = Math.max(rightMax[i + 1], height[i]);
            }

            int total = 0;
            for (int i = 0; i < W; i++) {
                int water = Math.min(leftMax[i], rightMax[i]) - height[i];
                if (water > 0) total += water;
            }

            System.out.println(total);
        }
    }
}
