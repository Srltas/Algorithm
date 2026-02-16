import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sb.append(Integer.parseInt(st.nextToken())).append(' ');

            List<Integer> line = new ArrayList<>();
            int totalSteps = 0;

            for (int i = 0; i < 20; i++) {
                int current = Integer.parseInt(st.nextToken());
                boolean insert = false;

                for (int j = 0; j < line.size(); j++) {
                    if (line.get(j) > current) {
                        totalSteps += (line.size() - j);
                        line.add(j, current);
                        insert = true;
                        break;
                    }
                }

                if (!insert) line.add(current);
            }
            sb.append(totalSteps).append('\n');
        }
        System.out.println(sb);
    }
}
