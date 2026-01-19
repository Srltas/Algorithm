import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        int[] lengths = new int[6];
        int maxW = 0, maxWIndex = 0;
        int maxH = 0, maxHIndex = 0;

        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            lengths[i] = len;

            if (dir == 1 || dir == 2) {
                if (len > maxW) {
                    maxW = len;
                    maxWIndex = i;
                }
            } else {
                if (len > maxH) {
                    maxH = len;
                    maxHIndex = i;
                }
            }
        }

        int leftMaxW = lengths[(maxWIndex + 5) % 6];
        int rightMaxW = lengths[(maxWIndex + 1) % 6];
        int h = Math.abs(leftMaxW - rightMaxW);

        int leftMaxH = lengths[(maxHIndex + 5) % 6];
        int rightMaxH = lengths[(maxHIndex + 1) % 6];
        int w = Math.abs(leftMaxH - rightMaxH);

        int big = maxW * maxH;
        int small = w * h;
        System.out.println((big - small) * K);
    }
}
