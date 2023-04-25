/**
 *
 * URL : https://www.acmicpc.net/problem/1613
 *
 * 역사
 *
 * 문제
 * 역사, 그 중에서도 한국사에 해박한 세준이는 많은 역사적 사건들의 전후 관계를 잘 알고 있다. 즉, 임진왜란이 병자호란보다 먼저 일어났으며, 무오사화가 기묘사화보다 먼저 일어났다는 등의 지식을 알고 있는 것이다.
 *
 * 세준이가 알고 있는 일부 사건들의 전후 관계들이 주어질 때, 주어진 사건들의 전후 관계도 알 수 있을까? 이를 해결하는 프로그램을 작성해 보도록 하자.
 *
 * 입력
 * 첫째 줄에 첫 줄에 사건의 개수 n(400 이하의 자연수)과 알고 있는 사건의 전후 관계의 개수 k(50,000 이하의 자연수)가 주어진다. 다음 k줄에는 전후 관계를 알고 있는 두 사건의 번호가 주어진다. 이는 앞에 있는 번호의 사건이 뒤에 있는 번호의 사건보다 먼저 일어났음을 의미한다. 물론 사건의 전후 관계가 모순인 경우는 없다. 다음에는 사건의 전후 관계를 알고 싶은 사건 쌍의 수 s(50,000 이하의 자연수)이 주어진다. 다음 s줄에는 각각 서로 다른 두 사건의 번호가 주어진다. 사건의 번호는 1보다 크거나 같고, N보다 작거나 같은 자연수이다.
 *
 * 출력
 * s줄에 걸쳐 물음에 답한다. 각 줄에 만일 앞에 있는 번호의 사건이 먼저 일어났으면 -1, 뒤에 있는 번호의 사건이 먼저 일어났으면 1, 어떤지 모르면(유추할 수 없으면) 0을 출력한다.
 *
 * 예제 입력 1
 * 5 5
 * 1 2
 * 1 3
 * 2 3
 * 3 4
 * 2 4
 * 3
 * 1 5
 * 2 4
 * 3 1
 * 예제 출력 1
 * 0
 * -1
 * 1
 */

package gold;

import java.io.*;
import java.util.StringTokenizer;

public class P1613 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new P1613().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] history = new boolean[N + 1][N + 1];

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            history[s][e] = true;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (history[i][k] && history[k][j]) {
                        history[i][j] = true;
                    }
                }
            }
        }

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (history[s][e]) {
                bw.write(-1 + System.lineSeparator());
            } else if (history[e][s]) {
                bw.write(1 + System.lineSeparator());
            } else {
                bw.write(0 + System.lineSeparator());
            }
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
