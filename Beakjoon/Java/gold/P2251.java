/**
 *
 * URL : https://www.acmicpc.net/problem/2251
 *
 * 물통
 *
 * 문제
 * 각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다. 처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다. 이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데, 이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다. 이 과정에서 손실되는 물은 없다고 가정한다.
 *
 * 이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 물의 양이 변할 수도 있다. 첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을 모두 구해내는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 세 정수 A, B, C가 주어진다.
 *
 * 출력
 * 첫째 줄에 공백으로 구분하여 답을 출력한다. 각 용량은 오름차순으로 정렬한다.
 *
 * 예제 입력 1
 * 8 9 10
 * 예제 출력 1
 * 1 2 8 9 10
 */

package gold;

import java.util.*;

public class P2251 {

    static int[] now;
    static boolean[][] visited;
    static List<Integer> answer = new ArrayList<>();
    static int[] sender = { 0, 0, 1, 1, 2, 2 };
    static int[] receiver = { 1, 2, 0, 2, 0, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        now = new int[3];
        now[0] = sc.nextInt();
        now[1] = sc.nextInt();
        now[2] = sc.nextInt();

        visited = new boolean[201][201];

        BFS();

        Collections.sort(answer);
        for (Integer integer : answer) {
            System.out.print(integer + " ");
        }
    }

    private static void BFS() {
        Queue<AB> q = new LinkedList<>();
        q.add(new AB(0, 0));
        visited[0][0] = true;
        answer.add(now[2]);

        while (!q.isEmpty()) {
            AB p = q.poll();
            int A = p.A;
            int B = p.B;
            int C = now[2] - A - B;
            for (int i = 0; i < 6; i++) {
                int[] next = { A, B, C };
                next[receiver[i]] += next[sender[i]];
                next[sender[i]] = 0;

                // 물이 넘칠 때
                if (next[receiver[i]] > now[receiver[i]]) {
                    // 초과하는 만큼 다시 이전 물통에 넣어 줌
                    next[sender[i]] = next[receiver[i]] - now[receiver[i]];
                    // 대상 물통에 최대로 채워줌
                    next[receiver[i]] = now[receiver[i]];
                }

                if (!visited[next[0]][next[1]]) {
                    visited[next[0]][next[1]] = true;
                    q.add(new AB(next[0], next[1]));

                    // A의 물의 양이 0일 때 C의 물의 무게를 정답 변수에 저장
                    if (next[0] == 0) {
                        answer.add(next[2]);
                    }
                }
            }
        }
    }

}
class AB {
    int A;
    int B;

    public AB(int A, int B) {
        this.A = A;
        this.B = B;
    }
}