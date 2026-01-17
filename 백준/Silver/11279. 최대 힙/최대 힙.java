import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Optional;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                sb.append(Optional.ofNullable(q.poll()).orElse(0)).append('\n');
                continue;
            }
            q.add(num);
        }
        System.out.println(sb);
    }
}
