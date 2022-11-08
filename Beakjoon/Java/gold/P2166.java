/**
 *
 * URL : https://www.acmicpc.net/problem/2166
 *
 * 다각형의 면적
 *
 * 문제
 * 2차원 평면상에 N(3 ≤ N ≤ 10,000)개의 점으로 이루어진 다각형이 있다. 이 다각형의 면적을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. 다음 N개의 줄에는 다각형을 이루는 순서대로 N개의 점의 x, y좌표가 주어진다. 좌표값은 절댓값이 100,000을 넘지 않는 정수이다.
 *
 * 출력
 * 첫째 줄에 면적을 출력한다. 면적을 출력할 때에는 소수점 아래 둘째 자리에서 반올림하여 첫째 자리까지 출력한다.
 *
 * 예제 입력 1
 * 4
 * 0 0
 * 0 10
 * 10 10
 * 10 0
 * 예제 출력 1
 * 100.0
 */

package gold;

import java.util.Scanner;

public class P2166 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[] x = new long[N + 1];
        long[] y = new long[N + 1];
        for (int i = 0; i < N; i++) {
            x[i] = sc.nextLong();
            y[i] = sc.nextLong();
        }

        // 마지막 점과 처음 점도 CCW 계산에 포함해야 함
        x[N] = x[0];
        y[N] = y[0];
        double result = 0;
        for (int i = 0; i < N; i++) {
            result += (x[i] * y[i + 1]) - (x[i + 1] * y[i]);
        }
        String answer = String.format("%.1f", Math.abs(result) / 2.0);
        System.out.println(answer);
    }
}
