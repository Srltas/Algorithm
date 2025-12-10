import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nA = Integer.parseInt(st.nextToken());
            int nB = Integer.parseInt(st.nextToken());

            TreeSet<Integer> setA = new TreeSet<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < nA; i++) {
                setA.add(Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < nB; i++) {
                int num = Integer.parseInt(st.nextToken());
                setA.remove(num);
            }

            StringBuilder sb = new StringBuilder();
            sb.append(setA.size()).append("\n");

            if (!setA.isEmpty()) {
                for (int num : setA) {
                    sb.append(num).append(" ");
                }
            }
            System.out.println(sb);
        }
    }
}
