import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<int[]> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int h = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty()) {
                if (stack.peek()[1] < h) {
                    stack.pop();
                } else {
                    sb.append(stack.peek()[0]).append(" ");
                    break;
                }
            }
            if (stack.isEmpty()) sb.append("0 ");
            stack.push(new int[] {i, h});
        }
        System.out.println(sb.toString().trim());
    }
}
