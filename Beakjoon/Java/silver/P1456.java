/**
 *
 * URL : https://www.acmicpc.net/problem/1456
 *
 * 거의 소수
 *
 * 문제
 * 어떤 수가 소수의 N제곱(N ≥ 2) 꼴일 때, 그 수를 거의 소수라고 한다.
 *
 * 두 정수 A와 B가 주어지면, A보다 크거나 같고, B보다 작거나 같은 거의 소수가 몇 개인지 출력한다.
 *
 * 입력
 * 첫째 줄에 왼쪽 범위 A와 오른쪽 범위 B가 공백 한 칸을 사이에 두고 주어진다.
 *
 * 출력
 * 첫째 줄에 총 몇 개가 있는지 출력한다.
 *
 * 제한
 * 1 ≤ A ≤ B ≤ 1014
 * 예제 입력 1  복사
 * 1 1000
 * 예제 출력 1  복사
 * 25
 * 예제 입력 2  복사
 * 1 10
 * 예제 출력 2  복사
 * 3
 * 예제 입력 3  복사
 * 5324 894739
 * 예제 출력 3  복사
 * 183
 */

package silver;

import java.util.Scanner;

public class P1456 {

    static final int MAX_SQRT = 10000001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long Min = sc.nextLong();
        long Max = sc.nextLong();
        long[] A = new long[MAX_SQRT];
        for (int i = 2; i < MAX_SQRT; i++) {
            A[i] = i;
        }

        for (int i = 2; i < Math.sqrt(MAX_SQRT); i++) {
            if (A[i] == 0) {
                continue;
            }
            for (int j = i + i; j < MAX_SQRT; j = j + i) {
                A[j] = 0;
            }
        }

        int count = 0;
        for (int i = 2; i < MAX_SQRT; i++) {
            if (A[i] != 0) {
                long temp = A[i];

                while ((double)A[i] <= (double)Max/(double)temp) {
                    if ((double)A[i] >= (double)Min/(double)temp) {
                        count++;
                    }
                    temp = temp * A[i];
                }
            }
        }
        System.out.println(count);
    }
}
