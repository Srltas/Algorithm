import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> nameToNum = new HashMap<>();
        String[] numToName = new String[N + 1];

        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            nameToNum.put(name, i);
            numToName[i] = name;
        }

        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            String query = br.readLine();
            if (Character.isDigit(query.charAt(0))) {
                int index = Integer.parseInt(query);
                sb.append(numToName[index]).append('\n');
            } else {
                sb.append(nameToNum.get(query)).append('\n');
            }
        }
        System.out.println(sb);
    }
}
