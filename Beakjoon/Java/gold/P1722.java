/**
 *
 * URL : https://www.acmicpc.net/problem/1722
 *
 * 순열의 순서
 *
 * 문제
 * 1부터 N까지의 수를 임의로 배열한 순열은 총 N! = N×(N-1)×…×2×1 가지가 있다.
 *
 * 임의의 순열은 정렬을 할 수 있다. 예를 들어  N=3인 경우 {1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}의 순서로 생각할 수 있다. 첫 번째 수가 작은 것이 순서상에서 앞서며, 첫 번째 수가 같으면 두 번째 수가 작은 것이, 두 번째 수도 같으면 세 번째 수가 작은 것이….
 *
 * N이 주어지면, 아래의 두 소문제 중에 하나를 풀어야 한다. k가 주어지면 k번째 순열을 구하고, 임의의 순열이 주어지면 이 순열이 몇 번째 순열인지를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 20)이 주어진다. 둘째 줄의 첫 번째 수는 소문제 번호이다. 1인 경우 k(1 ≤ k ≤ N!)를 입력받고, 2인 경우 임의의 순열을 나타내는 N개의 수를 입력받는다. N개의 수에는 1부터 N까지의 정수가 한 번씩만 나타난다.
 *
 * 출력
 * k번째 수열을 나타내는 N개의 수를 출력하거나, 몇 번째 수열인지를 출력하면 된다.
 *
 * 예제 입력 1
 * 4
 * 1 3
 * 예제 출력 1
 * 1 3 2 4
 * 예제 입력 2
 * 4
 * 2 1 3 2 4
 * 예제 출력 2
 * 3
 */

package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1722 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[N + 1];
        int[] S = new int[N + 1];
        long[] F = new long[N + 1];

        // 팩토리얼 초기화
        F[0] = 1;
        for (int i = 1; i <= N; i++) {
            F[i] = F[i - 1] * i;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int check = Integer.parseInt(st.nextToken());
        if (check == 1) {
            long K = Long.parseLong(st.nextToken());
            for (int i = 1; i <= N; i++) {
                for (int j = 1, cnt = 1; j <= N; j++) {
                    if (visited[j]) {
                        continue;
                    }

                    if (K <= F[N - i] * cnt) {
                        K -= (F[N - i] * (cnt - 1));
                        S[i] = j;
                        visited[j]= true;
                        break;
                    }
                    cnt++;
                }
            }
            for (int i = 1; i <= N; i++) {
                System.out.print(S[i] + " ");
            }
        } else if (check == 2) {
            long K = 1;
            for (int i = 1; i <= N; i++) {
                S[i] = Integer.parseInt(st.nextToken());

                long count = 0;
                for (int j = 1; j < S[i]; j++) {
                    if (!visited[j]) {
                        count++;
                    }
                }
                K += count * F[N - i];
                visited[S[i]] = true;
            }
            System.out.println(K);
        }

    }
}
