/**
 *
 * URL : https://www.acmicpc.net/problem/9661
 *
 * 돌 게임 7
 *
 * 문제
 * 돌 게임은 두 명이서 즐기는 재밌는 게임이다.
 *
 * 탁자 위에 돌 N개가 있다. 상근이와 창영이는 턴을 번갈아가면서 돌을 가져가며, 돌은 4x개 만큼 가져갈 수 있다. 즉, 가능한 개수는 1, 4, 16, 64, ...개 이다. 4x개만큼 돌을 가져갈 수 있는 방법이 없는 사람이 게임을 지게 된다.
 *
 * 두 사람이 완벽하게 게임을 했을 때, 이기는 사람을 구하는 프로그램을 작성하시오. 게임은 상근이가 먼저 시작한다.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. (1 ≤ N ≤ 1,000,000,000,000)
 *
 * 출력
 * 상근이가 게임을 이기면 SK를, 창영이가 게임을 이기면 CY을 출력한다.
 *
 * 예제 입력 1
 * 3
 * 예제 출력 1
 * SK
 */

package gold;

import java.util.Scanner;

public class P9661 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        System.out.println(N % 5 == 0 || N % 5 == 2 ? "CY" : "SK");
    }
}
