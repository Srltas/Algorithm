import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= N; i++) {
                q.offer(i);
            }

            StringBuilder sb = new StringBuilder();
            while (!q.isEmpty()) {
                sb.append(q.poll());
                if (q.isEmpty()) break;
                sb.append(" ");
                q.offer(q.poll());
            }
            System.out.println(sb);
        }
    }
}
