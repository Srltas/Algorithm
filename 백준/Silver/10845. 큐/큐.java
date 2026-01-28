import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Deque<Integer> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        while (N -- > 0) {
            String[] s = br.readLine().split(" ");

            switch (s[0]) {
                case "push":
                    int n = Integer.parseInt(s[1]);
                    q.offer(n);
                    break;
                case "pop":
                    sb.append(q.isEmpty() ? "-1" : q.poll()).append('\n');
                    break;
                case "size":
                    sb.append(q.size()).append('\n');
                    break;
                case "empty":
                    sb.append(q.isEmpty() ? "1" : "0").append('\n');
                    break;
                case "front":
                    sb.append(q.isEmpty() ? "-1" : q.peekFirst()).append('\n');
                    break;
                case "back":
                    sb.append(q.isEmpty() ? "-1" : q.peekLast()).append('\n');
                    break;
            }
        }
        System.out.println(sb);
    }
}
