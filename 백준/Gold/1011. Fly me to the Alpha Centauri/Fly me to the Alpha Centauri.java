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
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                int distance = y - x;
                int k = (int) Math.sqrt(distance);
                int k2 = (int) Math.pow(k, 2);
                if (k2 == distance) {
                    sb.append(2 * k - 1);
                } else if (k2 < distance && distance<= k2 + k) {
                    sb.append(2 * k);
                } else {
                    sb.append(2 * k + 1);
                }
                sb.append('\n');
            }
            System.out.println(sb);
        }
    }
}
