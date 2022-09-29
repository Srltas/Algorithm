/**
 *
 * URL : https://www.acmicpc.net/problem/1016
 *
 * 제곱 ㄴㄴ 수
 *
 * 문제
 * 어떤 정수 X가 1보다 큰 제곱수로 나누어 떨어지지 않을 때, 그 수를 제곱ㄴㄴ수라고 한다. 제곱수는 정수의 제곱이다. min과 max가 주어지면, min보다 크거나 같고, max보다 작거나 같은 제곱ㄴㄴ수가 몇 개 있는지 출력한다.
 *
 * 입력
 * 첫째 줄에 두 정수 min과 max가 주어진다.
 *
 * 출력
 * 첫째 줄에 min보다 크거나 같고, max보다 작거나 같은 제곱ㄴㄴ수의 개수를 출력한다.
 *
 * 제한
 * 1 ≤ min ≤ 1,000,000,000,000
 * min ≤ max ≤ min + 1,000,000
 * 예제 입력 1  복사
 * 1 10
 * 예제 출력 1  복사
 * 7
 * 예제 입력 2  복사
 * 15 15
 * 예제 출력 2  복사
 * 1
 * 예제 입력 3  복사
 * 1 1000
 * 예제 출력 3  복사
 * 608
 */

package gold;

import java.util.Scanner;

public class P1016 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long min = sc.nextLong();
        long max = sc.nextLong();
        // 최댓값과 최솟값의 차이만큼 배열 선언
        boolean[] check = new boolean[(int) (max - min + 1)];

        // 2의 제곱수인 4부터 max보다 작거나 같은 값까지 반복
        for (long i = 2; i * i <= max; i++) {
            // 제곱수
            long pow = i * i;
            long startIndex = min / pow;
            if (min % pow != 0) {
                // 나머지가 있으면 1을 더해야 min보다 큰 제곱수에서 시작
                startIndex++;
            }

            for (long j = startIndex; pow * j <= max; j++) {
                check[(int) ((j * pow) - min)] = true;
            }
        }

        int count = 0;
        for (int i = 0; i < max - min; i++) {
            if (!check[i]) {
                count++;
            }
        }

        System.out.println(count);
    }
}
