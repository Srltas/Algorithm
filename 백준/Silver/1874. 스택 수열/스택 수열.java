import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count = 1;

        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            while (count <= n) {
                stack.push(count);
                count++;
                sb.append("+").append('\n');
            }

            if (n == stack.peek()) {
                stack.pop();
                sb.append("-").append('\n');
            } else {
                System.out.println("NO");
                return;
            }
        }
        System.out.println(sb);
    }
}
