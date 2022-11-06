/**
 *
 * URL : https://www.acmicpc.net/problem/11758
 *
 * CCW
 *
 * 문제
 * 2차원 좌표 평면 위에 있는 점 3개 P1, P2, P3가 주어진다. P1, P2, P3를 순서대로 이은 선분이 어떤 방향을 이루고 있는지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 P1의 (x1, y1), 둘째 줄에 P2의 (x2, y2), 셋째 줄에 P3의 (x3, y3)가 주어진다. (-10,000 ≤ x1, y1, x2, y2, x3, y3 ≤ 10,000) 모든 좌표는 정수이다. P1, P2, P3의 좌표는 서로 다르다.
 *
 * 출력
 * P1, P2, P3를 순서대로 이은 선분이 반시계 방향을 나타내면 1, 시계 방향이면 -1, 일직선이면 0을 출력한다.
 *
 * 예제 입력 1
 * 1 1
 * 5 5
 * 7 3
 * 예제 출력 1
 * -1
 * 예제 입력 2
 * 1 1
 * 3 3
 * 5 5
 * 예제 출력 2
 * 0
 * 예제 입력 3
 * 1 1
 * 7 3
 * 5 5
 * 예제 출력 3
 * 1
 */

package gold;

import java.util.Scanner;

public class P11758 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        int x3 = sc.nextInt();
        int y3 = sc.nextInt();

        int result = ((x1 * y2) + (x2 * y3) + (x3 * y1)) - ((x2 * y1) + (x3 * y2) + (x1 * y3));

        if (result > 0) {
            System.out.println(1);
        } else if (result < 0) {
            System.out.println(-1);
        } else {
            System.out.println(0);
        }
    }
}
