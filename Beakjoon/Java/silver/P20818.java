/**
 *
 * URL : https://www.acmicpc.net/problem/20818
 *
 * Godishalsbandet
 *
 * 문제
 * 앨리스는 밥과 사탕 목걸이를 나누고 싶어합니다. 목걸이는 흰색과 파란색 사탕으로 구성되어 있습니다. 공평하게, Alice는 목걸이를 두 조각으로 나누고 각 조각에 같은 수의 사탕을 넣고 싶어합니다. 그러나 Alice는 흰색 사탕보다 파란색 사탕을 훨씬 더 좋아하므로 가능한 한 많은 파란색 사탕을 자신의 절반에 넣고 싶어합니다.
 *
 * 앨리스가 목걸이를 최적으로 자른다면, 앨리스가 자신의 몫으로 얻을 수 있는 가장 많은 파란색 사탕은 몇 개입니까?
 *
 * 입력
 * 입력은 목걸이를 설명하는 문자열이 있는 행으로 구성됩니다. 문자열은 B와 V, 로만 구성되며 총 짝수개의 문자를 가집니다.
 *
 * 출력
 * 앨리스가 목걸이 부분에서 얻을 수 있는 파란색 사탕의 최대 개수인 정수로 라인을 인쇄하십시오.
 *
 * 제한
 * 목걸이는 최대 1000000개의 사탕으로 구성됩니다.
 *
 * 예제 입력 1
 * BBVVBVVVBB
 * 예제 출력 1
 * 4
 * 예제 입력 2
 * BVBVBVBV
 * 예제 출력 2
 * 2
 */

package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P20818 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] list = br.readLine().toCharArray();

        int K = list.length / 2;

        int front = 0;
        int rear = 0;
        int count = 0;
        int max = 0;
        while (rear != list.length - 1) {
            if (list[front] == 'B') {
                count++;
            }

            if (front == K - 1) {
                max = count;
            }

            if (front >= K || rear >= K) {
                if (list[rear] == 'B') {
                    count--;
                }
                rear++;
                max = Math.max(max, count);
            }

            front++;

            if (front >= list.length) {
                front %= list.length;
            }
        }
        System.out.println(max);
    }
}
