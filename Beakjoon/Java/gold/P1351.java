/**
 *
 * URL : https://www.acmicpc.net/problem/1351
 *
 * 무한 수열
 *
 * 문제
 * 무한 수열 A는 다음과 같다.
 *
 * A0 = 1
 * Ai = A⌊i/P⌋ + A⌊i/Q⌋ (i ≥ 1)
 * N, P와 Q가 주어질 때, AN을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 3개의 정수 N, P, Q가 주어진다.
 *
 * 출력
 * 첫째 줄에 AN을 출력한다.
 *
 * 제한
 * 0 ≤ N ≤ 1012
 * 2 ≤ P, Q ≤ 109
 * 예제 입력 1
 * 7 2 3
 * 예제 출력 1
 * 7
 * 예제 입력 2
 * 0 2 3
 * 예제 출력 2
 * 1
 * 예제 입력 3
 * 10000000 3 3
 * 예제 출력 3
 * 32768
 * 예제 입력 4
 * 256 2 4
 * 예제 출력 4
 * 89
 * 예제 입력 5
 * 1 1000000 1000000
 * 예제 출력 5
 * 2
 */

package gold;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P1351 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    Map<Long, Long> map = new HashMap<>();

    long P, Q;

    public static void main(String[] args) throws IOException {
        new P1351().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());

        bw.write(calculation(N)+ System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    public long calculation(long n) {
        if (n == 0L) {
            return 1L;
        }

        if (map.containsKey(n)) {
            return map.get(n);
        }

        map.put(n, calculation(n / P) + calculation(n / Q));
        return map.get(n);
    }
}
