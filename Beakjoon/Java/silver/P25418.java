/**
 *
 * URL : https://www.acmicpc.net/problem/25418
 *
 * 정수 a를 k로 만들기
 *
 * 문제
 * 입력으로 양의 정수 A와 K가 주어지면, 아래 연산을 이용하여 A를 K로 변경하려고 한다. 정수 A를 변경할 때 사용할 수 있는 연산 종류는 다음과 같다.
 *
 * 연산 1: 정수 A에 1을 더한다.
 * 연산 2: 정수 A에 2를 곱한다.
 * 정수 A를 정수 K로 만들기 위해 필요한 최소 연산 횟수를 출력하자.
 *
 * 입력
 * 첫 번째 줄에 양의 정수 A와 K가 빈칸을 사이에 두고 순서대로 주어진다.
 *
 * 출력
 * 첫 번째 줄에 양의 정수 A를 양의 정수 K로 만들기 위해 필요한 최소 연산 횟수를 출력한다.
 *
 * 제한
 * 1 ≤ A < K ≤ 1,000,000
 *
 * 예제 입력 1
 * 5 10
 * 예제 출력 1
 * 1
 * 5(A), 10(연산 2)가 최소 연산이므로 정답은 1이다.
 *
 * 예제 입력 2
 * 7 77
 * 예제 출력 2
 * 7
 * 7(A), 8(연산 1), 9(연산 1), 18(연산 2), 19(연산 1), 38(연산 2), 76(연산 2), 77(연산 1)이 최소 연산이므로 정답은 7이다.
 *
 * 예제 입력 3
 * 1111 997651
 * 예제 출력 3
 * 850
 */

package silver;

import java.io.*;
import java.util.*;

public class P25418 {
    public static void main(String[] args) throws Exception {
        new P25418().solution();
    }

    private boolean[] visited;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        visited = new boolean[K + 1];
        bw.write(String.valueOf(BFS(A, K)));
        bw.flush();

        bw.close();
        br.close();
    }

    private int BFS(int a, int k) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(a);
        visited[a] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int n = queue.poll();
                if (n == k) {
                    return count;
                }

                if (n + 1 <= k && !visited[n + 1]) {
                    visited[n + 1] = true;
                    queue.offer(n + 1);
                }

                if (n * 2 <= k && !visited[n * 2]) {
                    visited[n * 2] = true;
                    queue.offer(n * 2);
                }
            }
            count++;
        }
        return 0;
    }
}
