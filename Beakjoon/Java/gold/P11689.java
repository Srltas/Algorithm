/**
 *
 * URL : https://www.acmicpc.net/problem/11689
 *
 * GCD(n, k) = 1
 *
 * 문제
 * 자연수 n이 주어졌을 때, GCD(n, k) = 1을 만족하는 자연수 1 ≤ k ≤ n 의 개수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 자연수 n (1 ≤ n ≤ 1012)이 주어진다.
 *
 * 출력
 * GCD(n, k) = 1을 만족하는 자연수 1 ≤ k ≤ n 의 개수를 출력한다.
 *
 * 예제 입력 1  복사
 * 1
 * 예제 출력 1  복사
 * 1
 * 예제 입력 2  복사
 * 5
 * 예제 출력 2  복사
 * 4
 * 예제 입력 3  복사
 * 10
 * 예제 출력 3  복사
 * 4
 * 예제 입력 4  복사
 * 45
 * 예제 출력 4  복사
 * 24
 * 예제 입력 5  복사
 * 99
 * 예제 출력 5  복사
 * 60
 */

package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P11689 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        long result = n;
        for (int p = 2; p <= Math.sqrt(n); p++) {
            if (n % p == 0) {
                result = result - result / p;
                while (n % p == 0) {
                    n /= p;
                }
            }
        }
        // 아직 소인수 구성이 남아 있을 때
        if (n > 1) {
            // 반복문에서 제곱근까지만 탐색했으므로 1개의 소인수가 누락되는 케이스
            result = result - result / n;
        }
        System.out.println(result);
    }
}
