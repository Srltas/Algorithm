import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            while (T-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int r1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());

                long dx = x1 - x2;
                long dy = y1 - y2;
                long d2 = dx * dx + dy * dy;

                long rp = r1 + r2;
                long rm = Math.abs(r1 - r2);
                long rp2 = rp * rp;
                long rm2 = rm * rm;

                long answer;
                if (d2 == 0 && r1 == r2) {
                    answer = -1;
                } else if (d2 == rp2 || d2 == rm2) {
                    answer = 1;
                } else if (rm2 < d2 && d2 < rp2) {
                    answer = 2;
                } else {
                    answer = 0;
                }
                sb.append(answer).append('\n');
            }
            System.out.println(sb);
        }
    }
}
