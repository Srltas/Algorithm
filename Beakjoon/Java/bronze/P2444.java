/**
 *
 * URL : https://www.acmicpc.net/problem/2444
 *
 * 별 찍기 - 7
 *
 * 문제
 * 예제를 보고 규칙을 유추한 뒤에 별을 찍어 보세요.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 100)이 주어진다.
 *
 * 출력
 * 첫째 줄부터 2×N-1번째 줄까지 차례대로 별을 출력한다.
 *
 * 예제 입력 1
 * 5
 * 예제 출력 1
 *     *
 *    ***
 *   *****
 *  *******
 * *********
 *  *******
 *   *****
 *    ***
 *     *
 */

package bronze;

import java.io.*;

public class P2444 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        new P2444().solution();
    }

    public void solution() throws IOException {
        int N = Integer.parseInt(br.readLine());

        int count = N;
        for (int i = 1; i <= (2 * N) - 1; i += 2) {
            count -= 1;
            StringBuffer sb = new StringBuffer();
            sb.append(" ".repeat(count)).append("*".repeat(i));
            bw.write(sb + System.lineSeparator());
        }

        for (int i = (2 * N) - 3; i >= 1; i -= 2) {
            count += 1;
            StringBuffer sb = new StringBuffer();
            sb.append(" ".repeat(count)).append("*".repeat(i));
            bw.write(sb + System.lineSeparator());
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
