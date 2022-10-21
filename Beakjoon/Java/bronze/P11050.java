/**
 *
 * URL : https://www.acmicpc.net/problem/11050
 *
 * 이항 계수 1
 *
 * 문제
 * 자연수 \(N\)과 정수 \(K\)가 주어졌을 때 이항 계수
 * \(\binom{N}{K}\)를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 \(N\)과 \(K\)가 주어진다. (1 ≤ \(N\) ≤ 10, 0 ≤ \(K\) ≤ \(N\))
 *
 * 출력
 *
 * \(\binom{N}{K}\)를 출력한다.
 *
 * 예제 입력 1
 * 5 2
 * 예제 출력 1
 * 10
 */

package bronze;

import java.util.Scanner;

public class P11050 {
//    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//        int K = sc.nextInt();
//
//        int[][] dp = new int[N + 1][N + 1];
//        for (int i = 0; i <= N; i++) {
//            dp[i][1] = i;   // i개 중 1개를 선택하는 경우의 수 i
//            dp[i][0] = 1;   // i개 중 0개를 선택하는 경우의 수 1
//            dp[i][i] = 1;   // i개 중 i개를 선택하는 경우의 수 1
//        }
//
//        for (int i = 2; i <= N; i++) {
//            for (int j = 2; j <= i; j++) {
//                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
//            }
//        }
//
//        System.out.println(dp[N][K]);
//    }

    private static int[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        dp = new int[N + 1][N + 1];

        System.out.println(getDP(N, K));
    }

    private static int getDP(int n, int k) {
        if (dp[n][k] > 0) {
            return dp[n][k];
        }

        if (k == 0 || n == k) {
            return dp[n][k] = 1;
        }

        return dp[n][k] = getDP(n - 1, k) + getDP(n - 1, k - 1);
    }
}
