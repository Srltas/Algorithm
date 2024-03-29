/**
 *
 * URL : https://www.acmicpc.net/problem/2440
 *
 * 별 찍기 - 3
 *
 * 문제
 * 첫째 줄에는 별 N개, 둘째 줄에는 별 N-1개, ..., N번째 줄에는 별 1개를 찍는 문제
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 100)이 주어진다.
 *
 * 출력
 * 첫째 줄부터 N번째 줄까지 차례대로 별을 출력한다.
 *
 * 예제 입력 1
 * 5
 * 예제 출력 1
 * *****
 * ****
 * ***
 * **
 * *
 */

package bronze;

import java.util.Scanner;

public class P2440 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for (int i = N; i > 0; i--) {
            System.out.println("*".repeat(i));
        }
    }
}
