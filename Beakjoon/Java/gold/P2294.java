/**
 *
 * URL : https://www.acmicpc.net/problem/2294
 *
 * 동전 2
 *
 * 문제
 * n가지 종류의 동전이 있다. 이 동전들을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 그러면서 동전의 개수가 최소가 되도록 하려고 한다. 각각의 동전은 몇 개라도 사용할 수 있다.
 *
 * 사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.
 *
 * 입력
 * 첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 동전의 가치는 100,000보다 작거나 같은 자연수이다. 가치가 같은 동전이 여러 번 주어질 수도 있다.
 *
 * 출력
 * 첫째 줄에 사용한 동전의 최소 개수를 출력한다. 불가능한 경우에는 -1을 출력한다.
 *
 * 예제 입력 1
 * 3 15
 * 1
 * 5
 * 12
 * 예제 출력 1
 * 3
 */

package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] DP = new int[10001];
        for (int i = 0; i < 10001; i++) {
            DP[i] = 10001;
        }

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            int coin = Integer.parseInt(br.readLine());
            if (coin <= 10000) {
                coins[i] = coin;
                DP[coin] = 1;
            }
        }

        for (int i = 1; i <= K; i++) {
            for (int j = 0; j < N; j++) {
                if (i == coins[j]) {
                    break;
                }

                if (i > coins[j]) {
                    DP[i] = Math.min(DP[i], DP[i - coins[j]] + 1);
                }

            }
        }

        if (DP[K] == 10001) {
            System.out.println(-1);
        } else {
            System.out.println(DP[K]);
        }
    }
}
