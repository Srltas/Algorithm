/**
 *
 * URL : https://www.acmicpc.net/problem/12761
 *
 * 돌다리
 *
 * 문제
 * 동규와 주미는 일직선 상의 돌 다리 위에있다. 돌의 번호는 0 부터 100,000 까지 존재하고 동규는
 * \(N\)번 돌 위에, 주미는
 * \(M\)번 돌 위에 위치하고 있다. 동규는 주미가 너무 보고싶기 때문에 최대한 빨리 주미에게 가기 위해
 * \(A, B\) 만큼의 힘을 가진 스카이 콩콩을 가져왔다. 동규가 정한 다리를 건너는 규칙은 턴 방식인데, 한 턴에 이동할 수 있는 거리는 이러하다. 현 위치에서 +1칸, -1칸을 이동할 수 있고, 스카이 콩콩을 이용해 현 위치에서
 * \(A\)나
 * \(B\)만큼 좌우로 점프할 수 있으며, 순간적으로 힘을 모아 현 위치의
 * \(A\)배나
 * \(B\)배의 위치로 이동을 할 수 있다. 예를 들어 지금 동규가 7번 돌 위에 있고 스카이 콩콩의 힘이 8이면 그냥 점프를 해서 15번 돌에 갈 수도 있고, 순간적으로 힘을 모아 56번 돌에 갈 수도 있다는 것이다. 주어진 8가지의 방법 중 적절한 방법을 골라서 최대한 빨리 동규가 주미를 만날 수 있게 도와주자. 단, 이동 과정에서 100,000보다 크거나 0보다 작은 번호의 돌은 존재하지 않으므로 갈 수 없고, 같은 방법을 계속 사용해도 되며 항상 도달할 수 있는 케이스만 주어진다.
 *
 * 입력
 * 입력의 첫 줄에 스카이 콩콩의 힘
 * \(A\)와
 * \(B\), 그리고 동규의 현재위치
 * \(N\), 주미의 현재 위치
 * \(M\)이 주어진다. (단,
 * \(2 \le A, B \le 30\) 이고
 * \(0 \le N, M \le 100,000\))
 *
 * 출력
 * 동규가 주미에게 도달하기 위한 최소한의 이동 횟수를 출력하라.
 *
 * 예제 입력 1
 * 2 3 1 20
 * 예제 출력 1
 * 4
 * 예제 입력 2
 * 3 7 2 98500
 * 예제 출력 2
 * 10
 */

package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P12761 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int A, B, N, M;
    int[] answer = new int[1000001];

    public static void main(String[] args) throws IOException {
        new P12761().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bw.write(BFS() + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    private int BFS() {
        int[] move = {1, -1, A, -A, B, -B};
        int[] jump = {A, B};

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        answer[N] = 1;

    while (!queue.isEmpty()) {
            int position = queue.poll();
            if (position == M) {
                return answer[position] - 1;
            }

            for (int i = 0; i < 6; i++) {
                int nextNum = position + move[i];
                if (checkRange(nextNum)) {
                    queue.offer(nextNum);
                    answer[nextNum] = answer[position] + 1;
                }
            }

            for (int i = 0; i < 2; i++) {
                int nextNum = position * jump[i];
                if (checkRange(nextNum)) {
                    queue.offer(nextNum);
                    answer[nextNum] = answer[position] + 1;
                }
            }
        }

        return answer[M] - 1;
    }

    private boolean checkRange(int num) {
        return 0 <= num && num <= 100000 && answer[num] == 0;
    }
}
