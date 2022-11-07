/**
 *
 * URL : https://www.acmicpc.net/problem/17387
 *
 * 선분 교차 2
 *
 * 문제
 * 2차원 좌표 평면 위의 두 선분 L1, L2가 주어졌을 때, 두 선분이 교차하는지 아닌지 구해보자. 한 선분의 끝 점이 다른 선분이나 끝 점 위에 있는 것도 교차하는 것이다.
 *
 * L1의 양 끝 점은 (x1, y1), (x2, y2), L2의 양 끝 점은 (x3, y3), (x4, y4)이다.
 *
 * 입력
 * 첫째 줄에 L1의 양 끝 점 x1, y1, x2, y2가, 둘째 줄에 L2의 양 끝 점 x3, y3, x4, y4가 주어진다.
 *
 * 출력
 * L1과 L2가 교차하면 1, 아니면 0을 출력한다.
 *
 * 제한
 * -1,000,000 ≤ x1, y1, x2, y2, x3, y3, x4, y4 ≤ 1,000,000
 * x1, y1, x2, y2, x3, y3, x4, y4는 정수
 * 선분의 길이는 0보다 크다.
 * 예제 입력 1
 * 1 1 5 5
 * 1 5 5 1
 * 예제 출력 1
 * 1
 * 예제 입력 2
 * 1 1 5 5
 * 6 10 10 6
 * 예제 출력 2
 * 0
 * 예제 입력 3
 * 1 1 5 5
 * 5 5 1 1
 * 예제 출력 3
 * 1
 * 예제 입력 4
 * 1 1 5 5
 * 3 3 5 5
 * 예제 출력 4
 * 1
 * 예제 입력 5
 * 1 1 5 5
 * 3 3 1 3
 * 예제 출력 5
 * 1
 * 예제 입력 6
 * 1 1 5 5
 * 5 5 9 9
 * 예제 출력 6
 * 1
 * 예제 입력 7
 * 1 1 5 5
 * 6 6 9 9
 * 예제 출력 7
 * 0
 * 예제 입력 8
 * 1 1 5 5
 * 5 5 1 5
 * 예제 출력 8
 * 1
 * 예제 입력 9
 * 1 1 5 5
 * 6 6 1 5
 * 예제 출력 9
 * 0
 */

package gold;

import java.util.Scanner;

public class P17387 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x1 = sc.nextLong();
        long y1 = sc.nextLong();
        long x2 = sc.nextLong();
        long y2 = sc.nextLong();
        long x3 = sc.nextLong();
        long y3 = sc.nextLong();
        long x4 = sc.nextLong();
        long y4 = sc.nextLong();

        if (isCross(x1, y1, x2, y2, x3, y3, x4, y4)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }

    private static boolean isCross(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        int ABC = CCW(x1, y1, x2, y2, x3, y3);
        int ABD = CCW(x1, y1, x2, y2, x4, y4);
        int CDA = CCW(x3, y3, x4, y4, x1, y1);
        int CDB = CCW(x3, y3, x4, y4, x2, y2);

        // 선분이 일직선 상에 있는 경우
        if (ABC * ABD == 0 && CDA * CDB == 0) {
            // 일직선 상에 있는 경우 따로 겹치는지 확인이 필요
            return isOverlab(x1, y1, x2, y2, x3, y3, x4, y4);
        } else if (ABC * ABD <= 0 && CDA * CDB <= 0) {
            return true;
        } else {
            return false;
        }
    }

    // 일직선 상태에서 선분이 겹치는 경우 확인
    private static boolean isOverlab(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        if (Math.min(x1, x2) <= Math.max(x3, x4)
                && Math.min(x3, x4) <= Math.max(x1, x2)
                && Math.min(y1, y2) <= Math.max(y3, y4)
                && Math.min(y3, y4) <= Math.max(y1, y2)) {
            return true;
        } else {
            return false;
        }
    }

    private static int CCW(long x1, long y1, long x2, long y2, long x3, long y3) {
        long result = ((x1 * y2) + (x2 * y3) + (x3 * y1)) - ((x2 * y1) + (x3 * y2) + (x1 * y3));

        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
