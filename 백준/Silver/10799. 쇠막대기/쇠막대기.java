import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String stick = br.readLine();

        int result = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < stick.length(); i++) {
            char s = stick.charAt(i);
            if (s == '(') {
                stack.push('(');
                continue;
            }

            if (s == ')') {
                stack.pop();

                // stack에 남아 있는 ( 개수가 레이저로 잘랐을 때 남는 조각
                if (stick.charAt(i - 1) == '(') {
                    result += stack.size();
                } else {
                    // 막대기에 끝으로 조각 한개를 더해준다.
                    result++;
                }
            }
        }

        bw.write(result + "\n");
        bw.flush();

        bw.close();
        br.close();
    }
}
