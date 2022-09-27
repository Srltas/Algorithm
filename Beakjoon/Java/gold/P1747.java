/**
 *
 * URL : https://www.acmicpc.net/problem/1747
 *
 * 소수 & 팰린드롬
 *
 * 문제
 * 어떤 수와 그 수의 숫자 순서를 뒤집은 수가 일치하는 수를 팰린드롬이라 부른다. 예를 들어 79,197과 324,423 등이 팰린드롬 수이다.
 *
 * 어떤 수 N (1 ≤ N ≤ 1,000,000)이 주어졌을 때, N보다 크거나 같고, 소수이면서 팰린드롬인 수 중에서, 가장 작은 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N이 주어진다.
 *
 * 출력
 * 첫째 줄에 조건을 만족하는 수를 출력한다.
 *
 * 예제 입력 1  복사
 * 31
 * 예제 출력 1  복사
 * 101
 */

package gold;

import java.util.Scanner;

public class P1747 {

    static final int MAX_SQRT = 10000001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[MAX_SQRT];
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

        int i = N;
        while (true) {
            if (A[i] != 0) {
                if (isPalindrome(A[i])) {
                    System.out.println(A[i]);
                    break;
                }
            }
            i++;
        }
    }

    static boolean isPalindrome(int target) {
        char[] temp = String.valueOf(target).toCharArray();

        int S = 0;
        int E = temp.length - 1;

        while (S < E) {
            if (temp[S] != temp[E]) {
                return false;
            }
            S++;
            E--;
        }

        return true;
    }
}
