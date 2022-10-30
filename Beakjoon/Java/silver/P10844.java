/**
 *
 * URL : https://www.acmicpc.net/problem/10844
 *
 * 쉬운 계단 수
 *
 * 문제
 * 45656이란 수를 보자.
 *
 * 이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다.
 *
 * N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구해보자. 0으로 시작하는 수는 계단수가 아니다.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
 *
 * 예제 입력 1
 * 1
 * 예제 출력 1
 * 9
 * 예제 입력 2
 * 2
 * 예제 출력 2
 * 17
 */

package silver;

import java.util.Scanner;

public class P10844 {

    private static final long MOD = 1000000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        long[][] D = new long[101][10];
        for (int i = 1; i < 10; i++) {
            D[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            D[i][0] = D[i - 1][1];
            D[i][9] = D[i - 1][8];
            for (int j = 1; j < 9; j++) {
                D[i][j] = (D[i - 1][j - 1] + D[i - 1][j + 1]) % MOD;
            }
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = (sum + D[N][i]) % MOD;    // 더할 때도 % 연산을 수행해야 함
        }
        System.out.println(sum);
    }
}
