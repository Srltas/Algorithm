import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        System.out.println(dfs(br.readLine()));
    }

    private static int dfs(String str) {
        if (str.length() == S.length()) return str.equals(S) ? 1 : 0;

        if (str.charAt(str.length() - 1) == 'A') {
            if (dfs(str.substring(0, str.length() - 1)) == 1) return 1;
        }

        if (str.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(str.substring(1));
            if (dfs(sb.reverse().toString()) == 1) return 1;
        }

        return 0;
    }
}
