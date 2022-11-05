/**
 *
 * URL : https://www.acmicpc.net/problem/5591
 *
 * 最大の和
 *
 * 문제
 * n 개의 정수로 구성된 수열 a 1 , a 2 , ..., a n 과 양의 정수 k (1 ≤ k ≤ n)가 주어진다. 이 때, 연속적으로 배열되는 k개의 정수의 합 S i = a i + a i+1 + ... +a i+k−1 (1 ≤ i ≤ n-k + 1)의 최대값을 출력 할 프로그램을 만드십시오.
 *
 * 입력
 * 첫 번째 행에는 양의 정수 n (1 ≤ n ≤ 100000)과 양의 정수 k (1 ≤ k ≤ n)가이 순서로 공백으로 구분되어 작성됩니다. 2 행째 이후의 제 1 + i 행째 (1 ≤ i ≤ n)에는, 수열의 i번째의 항 a i (-10000 ≤ a i ≤ 10000)가 쓰여져 있다.
 *
 * 출력
 * 하나의 행만으로 구성되며, 그 행은 S i 의 최대 값만 포함합니다.
 *
 * 예제 입력 1
 * 5 3
 * 2
 * 5
 * -4
 * 10
 * 3
 * 예제 출력 1
 * 11
 */

package silver;

import java.util.Scanner;

public class P5591 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] list = new int[N];
        for (int i = 0; i < N; i++) {
            list[i] = sc.nextInt();
        }

        int sum = 0;
        int max = 0;
        for (int i = 0; i < N; i++) {
            sum += list[i];

            if (i == K - 1) {
                max = sum;
            }

            if (i >= K) {
                sum -= list[i - K];
                max = Math.max(max, sum);
            }
        }
        System.out.println(max);
    }
}
