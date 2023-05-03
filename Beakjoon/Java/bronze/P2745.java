/**
 *
 * URL : https://www.acmicpc.net/problem/2745
 *
 * 진법 변환
 *
 * 문제
 * B진법 수 N이 주어진다. 이 수를 10진법으로 바꿔 출력하는 프로그램을 작성하시오.
 *
 * 10진법을 넘어가는 진법은 숫자로 표시할 수 없는 자리가 있다. 이런 경우에는 다음과 같이 알파벳 대문자를 사용한다.
 *
 * A: 10, B: 11, ..., F: 15, ..., Y: 34, Z: 35
 *
 * 입력
 * 첫째 줄에 N과 B가 주어진다. (2 ≤ B ≤ 36)
 *
 * B진법 수 N을 10진법으로 바꾸면, 항상 10억보다 작거나 같다.
 *
 * 출력
 * 첫째 줄에 B진법 수 N을 10진법으로 출력한다.
 *
 * 예제 입력 1
 * ZZZZZ 36
 * 예제 출력 1
 * 60466175
 */

package bronze;

import java.io.*;
import java.util.StringTokenizer;

public class P2745 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new P2745().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());

        int result = 0;
        int len = N.length();
        for (int i = 0; i < len; i++) {
            int value = N.charAt(len - i - 1) - 55;
            if (value < 10) {
                value += 7;
            }
            result += value * Math.pow(B, i);
        }
        bw.write(result + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }
}
