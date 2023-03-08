/**
 *
 * URL : https://www.acmicpc.net/problem/2417
 *
 * 정수 제곱근
 *
 * 문제
 * 정수가 주어지면, 그 수의 정수 제곱근을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 정수 n이 주어진다. (0 ≤ n < 263)
 *
 * 출력
 * 첫째 줄에 q2 ≥ n인 가장 작은 음이 아닌 정수 q를 출력한다.
 *
 * 예제 입력 1
 * 122333444455555
 * 예제 출력 1
 * 11060446
 */

package silver;

import java.io.*;

public class P2417 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        new P2417().solution();
    }

    public void solution() throws IOException {
        long N = Long.parseLong(br.readLine());

        long start = 0;
        long end = N;
        long result = Long.MAX_VALUE;

        while (start <= end) {
            long mid = (start + end) / 2;

            if ((long) Math.pow(mid,2) >= N) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        bw.write(result + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }
}
