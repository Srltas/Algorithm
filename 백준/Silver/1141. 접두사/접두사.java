import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int N = Integer.parseInt(br.readLine());
            Set<String> set = new HashSet<>();
            for (int i = 0; i < N; i++) {
                set.add(br.readLine());
            }

            List<String> words = new ArrayList<>(set);
            int count = 0;
            for (int i = 0; i < words.size(); i++) {
                String current = words.get(i);
                boolean prefix = false;
                for (int j = 0; j < words.size(); j++) {
                    if (i == j) continue;
                    String other = words.get(j);
                    if (other.startsWith(current)) {
                        prefix = true;
                        break;
                    }
                }

                if (!prefix) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
