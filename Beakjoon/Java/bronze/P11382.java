/**
 *
 * URL : https://www.acmicpc.net/problem/11382
 *
 * 꼬마 정민
 *
 * 문제
 * 꼬마 정민이는 이제 A + B 정도는 쉽게 계산할 수 있다. 이제 A + B + C를 계산할 차례이다!
 *
 * 입력
 * 첫 번째 줄에 A, B, C (1 ≤ A, B, C ≤ 1012)이 공백을 사이에 두고 주어진다.
 *
 * 출력
 * A+B+C의 값을 출력한다.
 *
 * 예제 입력 1
 * 77 77 7777
 * 예제 출력 1
 * 7931
 */

package bronze;

import java.io.*;
import java.util.StringTokenizer;

public class P11382 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long total = 0;
        while (st.hasMoreTokens()) {
            total += Long.parseLong(st.nextToken());
        }

        bw.write(total + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }
}
