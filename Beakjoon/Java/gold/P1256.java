/**
 *
 * URL : https://www.acmicpc.net/problem/1256
 *
 * 사전
 *
 * 문제
 * 동호와 규완이는 212호에서 문자열에 대해 공부하고 있다. 김진영 조교는 동호와 규완이에게 특별 과제를 주었다. 특별 과제는 특별한 문자열로 이루어 진 사전을 만드는 것이다. 사전에 수록되어 있는 모든 문자열은 N개의 "a"와 M개의 "z"로 이루어져 있다. 그리고 다른 문자는 없다. 사전에는 알파벳 순서대로 수록되어 있다.
 *
 * 규완이는 사전을 완성했지만, 동호는 사전을 완성하지 못했다. 동호는 자신의 과제를 끝내기 위해서 규완이의 사전을 몰래 참조하기로 했다. 동호는 규완이가 자리를 비운 사이에 몰래 사전을 보려고 하기 때문에, 문자열 하나만 찾을 여유밖에 없다.
 *
 * N과 M이 주어졌을 때, 규완이의 사전에서 K번째 문자열이 무엇인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 세 정수 N, M, K가 순서대로 주어진다.
 *
 * 출력
 * 첫째 줄에 규완이의 사전에서 K번째 문자열을 출력한다. 만약 규완이의 사전에 수록되어 있는 문자열의 개수가 K보다 작으면 -1을 출력한다.
 *
 * 제한
 * 1 ≤ N, M ≤ 100
 * 1 ≤ K ≤ 1,000,000,000
 * 예제 입력 1
 * 2 2 2
 * 예제 출력 1
 * azaz
 * 예제 입력 2
 * 2 2 6
 * 예제 출력 2
 * zzaa
 * 예제 입력 3
 * 10 10 1000000000
 * 예제 출력 3
 * -1
 * 예제 입력 4
 * 7 4 47
 * 예제 출력 4
 * aaazazaazaz
 */

package gold;

import java.util.Scanner;

public class P1256 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        long K = sc.nextLong();

        int[][] D = new int[N + M + 1][N + M + 1];
        for (int i = 0; i < N + M + 1; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    D[i][j] = 1;
                } else {
                    D[i][j] = D[i - 1][j] + D[i - 1][j - 1];
                    if (D[i][j] > 1000000000) {
                        D[i][j] = 1000000001;   // K 범위가 넘어가면 범위의 최댓값 저장
                    }
                }
            }
        }
        if (D[N + M][M] < K) {
            System.out.println(-1);
        } else {
            while (!(N == 0 && M == 0)) {
                // a를 선택했을 때 남은 문자로 만들 수 있는 모든 경우의 수가 K보다 크면
                if (D[N - 1 + M][M] >= K) {
                    System.out.print('a');
                    N--;
                } else {
                    System.out.print('z');
                    K = K - D[N - 1 + M][M];
                    M--;
                }
            }
        }
    }
}
