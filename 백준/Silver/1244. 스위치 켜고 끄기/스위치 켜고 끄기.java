import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        BitSet switches = new BitSet(N + 1);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            if (st.nextToken().equals("1")) {
                switches.set(i);
            }
        }

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (g == 1) {
                for (int i = n; i <= N; i += n) {
                    switches.flip(i);
                }
            } else {
                switches.flip(n);
                int l = n - 1;
                int r = n + 1;
                while (l >= 1 && r <= N && switches.get(l) == switches.get(r)) {
                    switches.flip(l--);
                    switches.flip(r++);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(switches.get(i) ? 1 : 0).append(' ');
            if (i % 20 == 0) sb.append('\n');
        }
        System.out.println(sb);
    }
}
