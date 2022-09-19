/**
 * URL : https://www.acmicpc.net/problem/1676
 *
 * 문제
 * N!에서 뒤에서부터 처음 0이 아닌 숫자가 나올 때까지 0의 개수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. (0 ≤ N ≤ 500)
 *
 * 출력
 * 첫째 줄에 구한 0의 개수를 출력한다.
 *
 * 예제 입력 1  복사
 * 10
 * 예제 출력 1  복사
 * 2
 * 예제 입력 2  복사
 * 3
 * 예제 출력 2  복사
 * 0
 */


package silver;

import java.util.Scanner;

public class P1676 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int count = 0;
        while (N >= 5) {
            count += N / 5;
            N /= 5;
        }

        System.out.println(count);
    }
}
