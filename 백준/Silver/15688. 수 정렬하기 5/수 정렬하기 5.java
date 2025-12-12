import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            br.lines()
                .limit(N)
                .mapToInt(Integer::parseInt)
                .sorted()
                .forEach(n -> sb.append(n).append('\n'));
            System.out.println(sb);
        }
    }
}
