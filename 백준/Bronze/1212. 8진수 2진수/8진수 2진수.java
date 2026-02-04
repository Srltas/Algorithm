import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final String[] base2  = {"000", "001", "010", "011", "100", "101", "110", "111"};
    static final String[] firstBase2  = {"0", "1", "10", "11", "100", "101", "110", "111"};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        if (str.equals("0")) {
            System.out.println(0);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int num = str.charAt(i) - '0';
            if (i == 0) {
                sb.append(firstBase2[num]);
            } else {
                sb.append(base2[num]);
            }
        }

        System.out.println(sb);
    }
}
