import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            TreeSet<Integer>[] levels = new TreeSet[101];
            for (int i = 1; i <= 100; i++) levels[i] = new TreeSet<>();

            HashMap<Integer, Integer> levelByP = new HashMap<>();
            StringBuilder sb = new StringBuilder();

            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                levels[L].add(P);
                levelByP.put(P, L);
            }

            int M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String order = st.nextToken();

                if ("add".equals(order)) {
                    int P = Integer.parseInt(st.nextToken());
                    int L = Integer.parseInt(st.nextToken());
                    Integer oldL = levelByP.get(P);
                    if (oldL != null && oldL != L) {
                        levels[oldL].remove(P);
                    }
                    levels[L].add(P);
                    levelByP.put(P, L);
                } else if ("solved".equals(order)) {
                    int P = Integer.parseInt(st.nextToken());
                    Integer L = levelByP.get(P);
                    levels[L].remove(P);
                } else if ("recommend".equals(order)) {
                    int x = Integer.parseInt(st.nextToken());
                    if (x == 1) {
                        for (int L = 100; L >= 1; L--) {
                            if (!levels[L].isEmpty()) {
                                sb.append(levels[L].last()).append('\n');
                                break;
                            }
                        }
                    } else {
                        for (int L = 1; L <= 100; L++) {
                            if (!levels[L].isEmpty()) {
                                sb.append(levels[L].first()).append('\n');
                                break;
                            }
                        }
                    }
                }
            }
            System.out.println(sb);
        }
    }
}
