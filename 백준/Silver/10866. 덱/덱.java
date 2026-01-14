import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.StringTokenizer;

public class Main {

    private static Deque<Integer> deque = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int num = 0;
            if (st.hasMoreTokens()) {
                num = Integer.parseInt(st.nextToken());
            }

            int result = dequeProgram(command, num);
            if (result == -2) continue;
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int dequeProgram(String command, int num) {
        int result = -2;

        switch (command) {
            case "push_back":
                deque.addLast(num);
                break;
            case "push_front":
                deque.addFirst(num);
                break;
            case "pop_front":
                result = Optional.ofNullable(deque.pollFirst()).orElse(-1);
                break;
            case "pop_back":
                result = Optional.ofNullable(deque.pollLast()).orElse(-1);
                break;
            case "size":
                result = deque.size();
                break;
            case "empty":
                result = deque.isEmpty() ? 1 : 0;
                break;
            case "front":
                result = Optional.ofNullable(deque.peekFirst()).orElse(-1);
                break;
            case "back":
                result = Optional.ofNullable(deque.peekLast()).orElse(-1);
        }

        return result;
    }
}
