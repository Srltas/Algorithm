import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        while (N != 0) {
            int rest = N % B;
            if (rest < 10) {
                sb.append((char) (rest + '0'));
            } else {
                sb.append((char) (rest - 10 + 'A'));
            }
            N = N / B;
        }
        System.out.println(sb.reverse());
    }
}
