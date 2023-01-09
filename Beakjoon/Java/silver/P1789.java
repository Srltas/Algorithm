/**
 *
 * URL : https://www.acmicpc.net/problem/1789
 *
 * 수들의 합
 *
 * 문제
 * 서로 다른 N개의 자연수의 합이 S라고 한다. S를 알 때, 자연수 N의 최댓값은 얼마일까?
 *
 * 입력
 * 첫째 줄에 자연수 S(1 ≤ S ≤ 4,294,967,295)가 주어진다.
 *
 * 출력
 * 첫째 줄에 자연수 N의 최댓값을 출력한다.
 *
 * 예제 입력 1
 * 200
 * 예제 출력 1
 * 19
 */

package silver;

import java.io.*;

public class P1789 {
    public static void main(String[] args) throws IOException {
        new P1789().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long N = Long.parseLong(br.readLine());
        long sum = 0L;

        int i = 0;
        while (N > sum) {
            sum += ++i;
        }

        bw.write((sum > N ? i - 1 : i) + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }
}
