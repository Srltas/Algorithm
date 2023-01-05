/**
 *
 * URL : https://www.acmicpc.net/problem/1312
 *
 * 소수
 *
 * 문제
 * 피제수(분자) A와 제수(분모) B가 있다. 두 수를 나누었을 때, 소숫점 아래 N번째 자리수를 구하려고 한다. 예를 들어, A=3, B=4, N=1이라면, A÷B=0.75 이므로 출력 값은 7이 된다.
 *
 * 입력
 * 첫 번째 줄에 A와 B(1 ≤ A, B ≤ 100,000), N(1 ≤ N ≤ 1,000,000)이 공백을 경계로 주어진다.
 *
 * 출력
 * A÷B를 했을 때, 소숫점 아래 N번째 수를 출력한다.
 *
 * 예제 입력 1
 * 25 7 5
 * 예제 출력 1
 * 2
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P1312 {
    public static void main(String[] args) throws IOException {
        new P1312().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int result = A % B;
        for (int i = 0; i < N - 1; i++) {
            result *= 10;
            result %= B;
        }

        result *= 10;
        bw.write((result/B) + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }
}
