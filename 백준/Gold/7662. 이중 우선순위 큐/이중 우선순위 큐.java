import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());

            while (T-- > 0) {
                int n = Integer.parseInt(br.readLine());
                TreeMap<Integer, Integer> Q = new TreeMap<>();

                for (int i = 0; i < n; i++) {
                    String line = br.readLine();
                    char op = line.charAt(0);
                    int val = Integer.parseInt(line.substring(2).trim());

                    if (op == 'I') {
                        Q.put(val, Q.getOrDefault(val, 0) + 1);
                    } else {
                        if (Q.isEmpty()) continue;

                        if (val == 1) {
                            int key = Q.lastKey();
                            int value = Q.get(key);
                            if (value == 1) Q.remove(key);
                            else Q.put(key, value - 1);
                        } else {
                            int key = Q.firstKey();
                            int value = Q.get(key);
                            if (value == 1) Q.remove(key);
                            else Q.put(key, value - 1);
                        }
                    }
                }

                if (Q.isEmpty()) {
                    System.out.println("EMPTY");
                } else {
                    System.out.println(Q.lastKey() + " " + Q.firstKey());
                }
            }
        }
    }
}
