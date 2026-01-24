import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char[] s1 = st.nextToken().toCharArray();
        char[] s2 = st.nextToken().toCharArray();

        int count = Integer.MAX_VALUE;
        for (int i = 0; i <= s2.length - s1.length; i++) {
            int c = 0;
            for (int j = 0; j < s1.length; j++) {
                if (s1[j] != s2[i + j]) {
                    c++;
                }
            }
            count = Math.min(count, c);
        }
        System.out.println(count);
    }
}
