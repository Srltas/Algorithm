import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        double[] num = new double[N];
        for (int i = 0; i < N; i++) num[i] = Double.parseDouble(br.readLine());

        Stack<Double> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if ('A' <= c && c <= 'Z') {
                stack.push(num[c - 'A']);
                continue;
            }

            double a = stack.pop();
            double b = stack.pop();
            switch (c) {
                case '*':
                    stack.push(a * b);
                    break;
                case '+':
                    stack.push(a + b);
                    break;
                case '/':
                    stack.push(b / a);
                    break;
                case '-':
                    stack.push(b - a);
                    break;
            }
        }
        System.out.printf("%.2f", stack.pop());
    }
}
