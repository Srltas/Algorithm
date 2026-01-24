import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] coin = {25, 10, 5, 1};
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int C = Integer.parseInt(br.readLine());
            for (int c : coin) {
                sb.append(C / c).append(" ");
                C %= c;
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
