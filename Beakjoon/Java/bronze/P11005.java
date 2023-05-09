/**
 *
 * URL : https://www.acmicpc.net/problem/11005
 *
 * 진법 변환 2
 *
 * 문제
 * 10진법 수 N이 주어진다. 이 수를 B진법으로 바꿔 출력하는 프로그램을 작성하시오.
 *
 * 10진법을 넘어가는 진법은 숫자로 표시할 수 없는 자리가 있다. 이런 경우에는 다음과 같이 알파벳 대문자를 사용한다.
 *
 * A: 10, B: 11, ..., F: 15, ..., Y: 34, Z: 35
 *
 * 입력
 * 첫째 줄에 N과 B가 주어진다. (2 ≤ B ≤ 36) N은 10억보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 10진법 수 N을 B진법으로 출력한다.
 *
 * 예제 입력 1
 * 60466175 36
 * 예제 출력 1
 * ZZZZZ
 */

package bronze;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P11005 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new P11005().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        List<Character> list = new ArrayList<>();
        while (N > 0) {
            if (N % B < 10) {
                list.add((char) (N % B + '0'));
            } else {
                list.add((char) (N % B - 10 + 'A'));
            }
            N /= B;
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            bw.write(list.get(i));
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
