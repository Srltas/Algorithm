/**
 *
 * URL : https://www.acmicpc.net/problem/1935
 *
 * 후위 표기식2
 *
 * 문제
 * 후위 표기식과 각 피연산자에 대응하는 값들이 주어져 있을 때, 그 식을 계산하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 피연산자의 개수(1 ≤ N ≤ 26) 가 주어진다. 그리고 둘째 줄에는 후위 표기식이 주어진다. (여기서 피연산자는 A~Z의 영대문자이며, A부터 순서대로 N개의 영대문자만이 사용되며, 길이는 100을 넘지 않는다) 그리고 셋째 줄부터 N+2번째 줄까지는 각 피연산자에 대응하는 값이 주어진다. 3번째 줄에는 A에 해당하는 값, 4번째 줄에는 B에 해당하는값 , 5번째 줄에는 C ...이 주어진다, 그리고 피연산자에 대응 하는 값은 100보다 작거나 같은 자연수이다.
 *
 * 후위 표기식을 앞에서부터 계산했을 때, 식의 결과와 중간 결과가 -20억보다 크거나 같고, 20억보다 작거나 같은 입력만 주어진다.
 *
 * 출력
 * 계산 결과를 소숫점 둘째 자리까지 출력한다.
 *
 * 예제 입력 1
 * 5
 * ABC*+DE/-
 * 1
 * 2
 * 3
 * 4
 * 5
 * 예제 출력 1
 * 6.20
 * 예제 입력 2
 * 1
 * AA+A+
 * 1
 * 예제 출력 2
 * 3.00
 */

package silver;

import java.io.*;
import java.util.Stack;

public class P1935 {
    private double[] nums = new double[26];

    public static void main(String[] args) throws IOException {
        new P1935().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String st = br.readLine();


        for (int i = 0; i < N; i++) {
            nums[i] = Double.parseDouble(br.readLine());
        }

        bw.write(String.format("%.2f" + System.lineSeparator(), calculation(st)));
        bw.flush();

        bw.close();
        br.close();
    }

    private double calculation(String st) {
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < st.length(); i++) {
            char c = st.charAt(i);
            double x = 0;
            double y = 0;

            switch (c) {
                case '+':
                    x = stack.pop();
                    y = stack.pop();
                    stack.push(x + y);
                    break;
                case '-':
                    x = stack.pop();
                    y = stack.pop();
                    stack.push(y - x);
                    break;
                case '*':
                    x = stack.pop();
                    y = stack.pop();
                    stack.push(x * y);
                    break;
                case '/':
                    x = stack.pop();
                    y = stack.pop();
                    stack.push(y / x);
                    break;
                default:
                    stack.push(nums[(c - 65)]);
            }
        }
        return stack.pop();
    }
}
