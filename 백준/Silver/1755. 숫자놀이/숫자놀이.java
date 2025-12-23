import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String[] words = {
        "zero", "one", "two", "three", "four",
        "five", "six", "seven", "eight", "nine"
    };

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            List<Node> list = new ArrayList<>();
            for (int i = M; i <= N; i++) {
                String s = intToString(i);
                list.add(new Node(i, s));
            }

            list.sort(Comparator.comparing(o -> o.word));

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i).num).append(" ");
                if ((i + 1) % 10 == 0) {
                    sb.append("\n");
                }
            }

            System.out.println(sb);
        }
    }

    static String intToString(int n) {
        if (n < 10) {
            return words[n];
        } else {
            return words[n / 10] + " " + words[n % 10];
        }
    }

    static class Node {
        int num;
        String word;
        public Node(int num, String word) {
            this.num = num;
            this.word = word;
        }
    }
}
