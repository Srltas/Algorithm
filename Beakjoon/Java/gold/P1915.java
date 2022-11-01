/**
 *
 * URL : https://www.acmicpc.net/problem/1915
 *
 * 가장 큰 정사각형
 *
 * 문제
 * n×m의 0, 1로 된 배열이 있다. 이 배열에서 1로 된 가장 큰 정사각형의 크기를 구하는 프로그램을 작성하시오.
 *
 * 0	1	0	0
 * 0	1	1	1
 * 1	1	1	0
 * 0	0	1	0
 * 위와 같은 예제에서는 가운데의 2×2 배열이 가장 큰 정사각형이다.
 *
 * 입력
 * 첫째 줄에 n, m(1 ≤ n, m ≤ 1,000)이 주어진다. 다음 n개의 줄에는 m개의 숫자로 배열이 주어진다.
 *
 * 출력
 * 첫째 줄에 가장 큰 정사각형의 넓이를 출력한다.
 *
 * 예제 입력 1
 * 4 4
 * 0100
 * 0111
 * 1110
 * 0010
 * 예제 출력 1
 * 4
 */

package gold;

import java.util.Scanner;

public class P1915 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        long[][] D = new long[N + 1][M + 1];
        long max = 0;
        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < M; j++) {
                D[i][j] = Long.parseLong(String.valueOf(line.charAt(j)));
                if (D[i][j] == 1 && j > 0 && i > 0) {
                    // 위, 왼쪽, 왼쪽 위(대각선) 중 가장 작은 값에 1을 더한다
                    D[i][j] = Math.min(D[i - 1][j - 1], Math.min(D[i - 1][j], D[i][j - 1])) + D[i][j];
                }
                if (max < D[i][j]) {
                    max = D[i][j];
                }
            }
        }
        System.out.println(max * max);
    }
}
