/**
 *
 * URL : https://www.acmicpc.net/problem/9470
 *
 * Strahler 순서
 *
 * 문제
 * 지질학에서 하천계는 유향그래프로 나타낼 수 있다. 강은 간선으로 나타내며, 물이 흐르는 방향이 간선의 방향이 된다. 노드는 호수나 샘처럼 강이 시작하는 곳, 강이 합쳐지거나 나누어지는 곳, 바다와 만나는 곳이다.
 *
 *
 *
 * 네모 안의 숫자는 순서를 나타내고, 동그라미 안의 숫자는 노드 번호를 나타낸다.
 *
 * 하천계의 Strahler 순서는 다음과 같이 구할 수 있다.
 *
 * 강의 근원인 노드의 순서는 1이다.
 * 나머지 노드는 그 노드로 들어오는 강의 순서 중 가장 큰 값을 i라고 했을 때, 들어오는 모든 강 중에서 Strahler 순서가 i인 강이 1개이면 순서는 i, 2개 이상이면 순서는 i+1이다.
 * 하천계의 순서는 바다와 만나는 노드의 순서와 같다. 바다와 만나는 노드는 항상 1개이며, 위의 그림의 Strahler 순서는 3이다.
 *
 * 하천계의 정보가 주어졌을 때, Strahler 순서를 구하는 프로그램을 작성하시오.
 *
 * 실제 강 중에서 Strahler 순서가 가장 큰 강은 아마존 강(12)이며, 미국에서 가장 큰 값을 갖는 강은 미시시피 강(10)이다.
 *
 * 노드 M은 항상 바다와 만나는 노드이다.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 수 T (1 ≤ T ≤ 1000)가 주어진다.
 *
 * 각 테스트 케이스의 첫째 줄에는 K, M, P가 주어진다. K는 테스트 케이스 번호, M은 노드의 수, P는 간선의 수이다. (2 ≤ M ≤ 1000) 다음 P개 줄에는 간선의 정보를 나타내는 A, B가 주어지며, A에서 B로 물이 흐른다는 뜻이다. (1 ≤ A, B ≤ M) M은 항상 바다와 만나는 노드이며, 밖으로 향하는 간선은 존재하지 않는다.
 *
 * 출력
 * 각 테스트 케이스마다 테스트 케이스 번호와 입력으로 주어진 하천계의 Strahler 순서를 한 줄에 하나씩 출력한다.
 *
 * 예제 입력 1
 * 1
 * 1 7 8
 * 1 3
 * 2 3
 * 6 4
 * 3 4
 * 3 5
 * 6 7
 * 5 7
 * 4 7
 * 예제 출력 1
 * 1 3
 */

package gold;

import java.io.*;
import java.util.*;

public class P9470 {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new P9470().solution();
    }

    public void solution() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            strahler(K, M, P);
        }
        bw.flush();

        bw.close();
        br.close();
    }

    public void strahler(int K, int M, int P) throws IOException {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= M; i++) {
            list.add(new ArrayList<>());
        }

        int[] indegree = new int[M + 1];
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            list.get(A).add(B);
            indegree[B]++;
        }

        int[][] strahlers = new int[M + 1][M + 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < M; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                strahlers[i][i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : list.get(now)) {
                strahlers[now][next] = strahlers[now][now];
                indegree[next]--;

                if (indegree[next] == 0) {
                    int max = 0;
                    boolean isTwice = false;

                    for (int i = 1; i < M; i++) {
                        int degree = strahlers[i][next];
                        if (degree > max) {
                            max = degree;
                            isTwice = false;
                        } else if (max == degree) {
                            isTwice = true;
                        }
                    }
                    strahlers[next][next] = isTwice ? max + 1 : max;
                    queue.offer(next);
                }
            }
        }
        bw.write(K + " " + strahlers[M][M] + System.lineSeparator());
    }
}
