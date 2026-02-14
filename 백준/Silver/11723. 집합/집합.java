import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = parseInt(br.readLine());
        StringTokenizer st;
        Set<Integer> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            switch (str) {
                case "add":
                    set.add(parseInt(st.nextToken()));
                    break;
                case "remove":
                    set.remove(parseInt(st.nextToken()));
                    break;
                case "check":
                    sb.append(set.contains(parseInt(st.nextToken())) ? "1" : "0").append('\n');
                    break;
                case "toggle":
                    int n = parseInt(st.nextToken());
                    if (set.contains(n)) set.remove(n);
                    else set.add(n);
                    break;
                case "all":
                    for (int i = 1; i < 21; i++) set.add(i);
                    break;
                case "empty":
                    set.clear();
                    break;
            }
        }
        System.out.println(sb);
    }
}
