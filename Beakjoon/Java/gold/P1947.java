/**
 *
 * URL : https://www.acmicpc.net/problem/1947
 *
 * 선문 전달
 *
 * 문제
 * 이번 ACM-ICPC 대회에 참가한 모든 사람들은 선물을 하나씩 준비했다.
 *
 * 대회가 끝나고 난 후에 각자 선물을 전달하려고 할 때, 선물을 나누는 경우의 수를 구하는 프로그램을 작성하시오.
 *
 * 모든 사람은 선물을 하나씩 받으며, 자기의 선물을 자기가 받는 경우는 없다.
 *
 * 입력
 * 첫째 줄에 ACM-ICPC 대회에 참가한 학생의 수 N(1 ≤ N ≤ 1,000,000)이 주어진다.
 *
 * 출력
 * 경우의 수를 1,000,000,000으로 나눈 나머지를 첫째 줄에 출력한다.
 *
 * 예제 입력 1
 * 5
 * 예제 출력 1
 * 44
 * 예제 입력 2
 * 4
 * 예제 출력 2
 * 9
 * 예제 입력 3
 * 3
 * 예제 출력 3
 * 2
 * 예제 입력 4
 * 21
 * 예제 출력 4
 * 50944540
 */

package gold;

import java.util.Scanner;

public class P1947 {

    static final long MOD = 1000000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[] D = new long[1000001];

        D[1] = 0;
        D[2] = 1;
        for (int i = 3; i <= N; i++) {
            D[i] = (i - 1) * (D[i - 1] + D[i - 2]) % MOD;
        }

        System.out.println(D[N]);
    }
}
