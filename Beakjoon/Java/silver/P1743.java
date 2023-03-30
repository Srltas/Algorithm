/**
 *
 * URL : https://www.acmicpc.net/problem/1743
 *
 * 음식물 피하기
 *
 * 문제
 * 코레스코 콘도미니엄 8층은 학생들이 3끼의 식사를 해결하는 공간이다. 그러나 몇몇 비양심적인 학생들의 만행으로 음식물이 통로 중간 중간에 떨어져 있다. 이러한 음식물들은 근처에 있는 것끼리 뭉치게 돼서 큰 음식물 쓰레기가 된다.
 *
 * 이 문제를 출제한 선생님은 개인적으로 이러한 음식물을 실내화에 묻히는 것을 정말 진정으로 싫어한다. 참고로 우리가 구해야 할 답은 이 문제를 낸 조교를 맞추는 것이 아니다.
 *
 * 통로에 떨어진 음식물을 피해가기란 쉬운 일이 아니다. 따라서 선생님은 떨어진 음식물 중에 제일 큰 음식물만은 피해 가려고 한다.
 *
 * 선생님을 도와 제일 큰 음식물의 크기를 구해서 “10ra"를 외치지 않게 도와주자.
 *
 * 입력
 * 첫째 줄에 통로의 세로 길이 N(1 ≤ N ≤ 100)과 가로 길이 M(1 ≤ M ≤ 100) 그리고 음식물 쓰레기의 개수 K(1 ≤ K ≤ N×M)이 주어진다.  그리고 다음 K개의 줄에 음식물이 떨어진 좌표 (r, c)가 주어진다.
 *
 * 좌표 (r, c)의 r은 위에서부터, c는 왼쪽에서부터가 기준이다. 입력으로 주어지는 좌표는 중복되지 않는다.
 *
 * 출력
 * 첫째 줄에 음식물 중 가장 큰 음식물의 크기를 출력하라.
 *
 * 예제 입력 1
 * 3 4 5
 * 3 2
 * 2 2
 * 3 1
 * 2 3
 * 1 1
 * 예제 출력 1
 * 4
 */

package silver;

import java.io.*;
import java.util.*;

public class P1743 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] dn = {0, 0, -1, 1};
    int[] dm = {-1, 1, 0, 0};

    int N, M, K;
    boolean[][] array;
    int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        new P1743().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        array = new boolean[N + 1][M + 1];
        List<Node> list = new ArrayList<>();
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            array[n][m] = true;
            list.add(new Node(n, m));
        }

        for (Node node : list) {
            if (array[node.n][node.m]) {
                maxValue = Math.max(maxValue, BFS(node));
            }
        }

        bw.write(maxValue + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    public int BFS(Node node) {
        int count = 1;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        array[node.n][node.m] = false;

        while (!queue.isEmpty()) {
            Node nextNode = queue.poll();

            for (int i = 0; i < 4; i++) {
                int n = nextNode.n + dn[i];
                int m = nextNode.m + dm[i];

                if (n > 0 && m > 0 && n <= N && m <= M && array[n][m]) {
                    queue.offer(new Node(n, m));
                    array[n][m] = false;
                    count++;
                }
            }
        }

        return count;
    }

    static class Node {
        public int n;
        public int m;

        public Node(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }
}
