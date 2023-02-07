/**
 *
 * URL : https://www.acmicpc.net/problem/2637
 *
 * 장난감 조립
 *
 * 문제
 * 우리는 어떤 장난감을 여러 가지 부품으로 조립하여 만들려고 한다. 이 장난감을 만드는데는 기본 부품과 그 기본 부품으로 조립하여 만든 중간 부품이 사용된다. 기본 부품은 다른 부품을 사용하여 조립될 수 없는 부품이다. 중간 부품은 또 다른 중간 부품이나 기본 부품을 이용하여 만들어지는 부품이다.
 *
 * 예를 들어보자. 기본 부품으로서 1, 2, 3, 4가 있다. 중간 부품 5는 2개의 기본 부품 1과 2개의 기본 부품 2로 만들어진다. 그리고 중간 부품 6은 2개의 중간 부품 5, 3개의 기본 부품 3과 4개의 기본 부품 4로 만들어진다. 마지막으로 장난감 완제품 7은 2개의 중간 부품 5, 3개의 중간 부품 6과 5개의 기본 부품 4로 만들어진다. 이런 경우에 장난감 완제품 7을 만드는데 필요한 기본 부품의 개수는 1번 16개, 2번 16개, 3번 9개, 4번 17개이다.
 *
 * 이와 같이 어떤 장난감 완제품과 그에 필요한 부품들 사이의 관계가 주어져 있을 때 하나의 장난감 완제품을 조립하기 위하여 필요한 기본 부품의 종류별 개수를 계산하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에는 자연수 N(3 ≤ N ≤ 100)이 주어지는데, 1부터 N-1까지는 기본 부품이나 중간 부품의 번호를 나타내고, N은 완제품의 번호를 나타낸다. 그리고 그 다음 줄에는 자연수 M(3 ≤ M ≤ 100)이 주어지고, 그 다음 M개의 줄에는 어떤 부품을 완성하는데 필요한 부품들 간의 관계가 3개의 자연수 X, Y, K로 주어진다. 이 뜻은 "중간 부품이나 완제품 X를 만드는데 중간 부품 혹은 기본 부품 Y가 K개 필요하다"는 뜻이다. 두 중간 부품이 서로를 필요로 하는 경우가 없다.
 *
 * 출력
 * 하나의 완제품을 조립하는데 필요한 기본 부품의 수를 한 줄에 하나씩 출력하되(중간 부품은 출력하지 않음), 반드시 기본 부품의 번호가 작은 것부터 큰 순서가 되도록 한다. 각 줄에는 기본 부품의 번호와 소요 개수를 출력한다.
 *
 * 정답은 2,147,483,647 이하이다.
 *
 * 예제 입력 1
 * 7
 * 8
 * 5 1 2
 * 5 2 2
 * 7 5 2
 * 6 5 2
 * 6 3 3
 * 6 4 4
 * 7 6 3
 * 7 4 5
 * 예제 출력 1
 * 1 16
 * 2 16
 * 3 9
 * 4 17
 */

package gold;

import java.io.*;
import java.util.*;

public class P2637 {
    public static void main(String[] args) throws IOException {
        new P2637().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<List<Integer>> list = new ArrayList<>();
        for (int n = 0; n <= N; n++) {
            list.add(new ArrayList<>());
        }

        int[] indegree = new int[N + 1];
        int[][] weights = new int[N + 1][N + 1];
        for (int m = 0; m < M; m++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            list.get(X).add(Y);
            indegree[Y]++;
            weights[X][Y] = K;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[N + 1];
        for (int n = 1; n <= N; n++) {
            if (indegree[n] == 0) {
                result[n] = 1;
                queue.offer(n);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : list.get(now)) {
                int weight = weights[now][next];
                result[next] += result[now] * weight;

                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        for (int n = 1; n <= N; n++) {
            if (list.get(n).size() == 0) {
                bw.write(n + " " + result[n] + System.lineSeparator());
            }
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
