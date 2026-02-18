import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        if (N == 0) {
            System.out.println(1);
            return;
        }

        int upCount = 0;
        int sameCount = 0;
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int n = Integer.parseInt(st.nextToken());
            if (n > s) upCount++;
            if (s == n) sameCount++;
        }

        System.out.println(upCount + sameCount + 1 > P ? "-1" : upCount + 1);
    }
}
