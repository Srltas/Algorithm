import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String str = br.readLine();
            int K = Integer.parseInt(br.readLine());

            int min = Integer.MAX_VALUE;
            int max = 0;

            List<Integer>[] charIndexes = new ArrayList[26];
            for (int i = 0; i < 26; i++) {
                charIndexes[i] = new ArrayList<>();
            }

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                charIndexes[c - 'a'].add(i);
            }

            for (int i = 0; i < 26; i++) {
                List<Integer> list = charIndexes[i];
                if (list.size() < K) continue;

                for (int j = 0; j <= list.size() - K; j++) {
                    int start = list.get(j);
                    int end = list.get(j + K - 1);
                    int length = end - start + 1;

                    min = Math.min(min, length);
                    if (str.charAt(start) == str.charAt(end)) {
                        max = Math.max(max, length);
                    }
                }
            }

            if (min == Integer.MAX_VALUE || max == 0) {
                sb.append("-1");
            } else {
                sb.append(min + " " + max);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
