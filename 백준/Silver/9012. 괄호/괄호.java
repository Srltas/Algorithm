import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            Stack<Character> stack = new Stack<>();
            for (char c : br.readLine().toCharArray()) {
                if (c == '(') {
                    stack.push(c);
                } else {
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                }
            }
            sb.append(stack.isEmpty() ? "YES" : "NO").append('\n');
        }
        System.out.println(sb);
    }
}
