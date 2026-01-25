import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int count = 0;
        while (N-- > 0) {
            String str = br.readLine();
            if (set.contains(str)) continue;
            count++;
            addCycles(str);
        }
        System.out.println(count);
    }

    private static void addCycles(String s) {
        int len = s.length();
        String doubled = s + s;
        for (int i = 0; i < len; i++) {
            set.add(doubled.substring(i, i + len));
        }
    }
}
