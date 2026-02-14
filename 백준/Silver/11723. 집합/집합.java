import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());

        int S = 0;
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int x;

            switch (op) {
                case "add":
                    x = Integer.parseInt(st.nextToken());
                    S |= (1 << x);
                    break;
                case "remove":
                    x = Integer.parseInt(st.nextToken());
                    S &= ~(1 << x);
                    break;
                case "check":
                    x = Integer.parseInt(st.nextToken());
                    sb.append((S & (1 << x)) != 0 ? "1\n" : "0\n");
                    break;
                case "toggle":
                    x = Integer.parseInt(st.nextToken());
                    S ^= (1 << x);
                    break;
                case "all":
                    S = (1 << 21) - 2;
                    break;
                case "empty":
                    S = 0;
                    break;
            }
        }
        System.out.println(sb);
    }
}
