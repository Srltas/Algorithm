import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static final char NEW_LINE = '\n';
    private static Deque<Integer> deque = new ArrayDeque<>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.startsWith("push")) {
                int num = Integer.parseInt(st.nextToken());
                executePush(command, num);
            } else {
                executePrint(command);
            }

        }
        System.out.println(sb);
    }

    private static void executePush(String command, int num) {
        if (command.equals("push_front")) {
            deque.offerFirst(num);
        } else {
            deque.offerLast(num);
        }
    }

    private static void executePrint(String command) {
        switch (command) {
            case "pop_front":
                sb.append(deque.isEmpty() ? -1 : deque.pollFirst()).append(NEW_LINE);
                break;
            case "pop_back":
                sb.append(deque.isEmpty() ? -1 : deque.pollLast()).append(NEW_LINE);
                break;
            case "size":
                sb.append(deque.size()).append(NEW_LINE);
                break;
            case "empty":
                sb.append(deque.isEmpty() ? 1 : 0).append(NEW_LINE);
                break;
            case "front":
                sb.append(deque.isEmpty() ? -1 : deque.peekFirst()).append(NEW_LINE);
                break;
            case "back":
                sb.append(deque.isEmpty() ? -1 : deque.peekLast()).append(NEW_LINE);
                break;
        }
    }
}
