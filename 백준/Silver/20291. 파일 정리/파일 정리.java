import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            Map<String, Integer> map = new TreeMap<>();
            for (int i = 0; i < N; i++) {
                String fileName = br.readLine();
                int dot = fileName.indexOf('.');
                String extension = fileName.substring(dot + 1);
                map.put(extension, map.getOrDefault(extension, 0) + 1);
            }

            StringBuilder sb = new StringBuilder();
            for (String key : map.keySet()) {
                sb.append(key).append(" ").append(map.get(key)).append("\n");
            }
            System.out.println(sb);
        }
    }
}
