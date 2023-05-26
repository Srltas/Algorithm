/**
 *
 * URL : https://www.acmicpc.net/problem/1261
 *
 * 알고스팟
 *
 * 문제
 * 알고스팟 운영진이 모두 미로에 갇혔다. 미로는 N*M 크기이며, 총 1*1크기의 방으로 이루어져 있다. 미로는 빈 방 또는 벽으로 이루어져 있고, 빈 방은 자유롭게 다닐 수 있지만, 벽은 부수지 않으면 이동할 수 없다.
 *
 * 알고스팟 운영진은 여러명이지만, 항상 모두 같은 방에 있어야 한다. 즉, 여러 명이 다른 방에 있을 수는 없다. 어떤 방에서 이동할 수 있는 방은 상하좌우로 인접한 빈 방이다. 즉, 현재 운영진이 (x, y)에 있을 때, 이동할 수 있는 방은 (x+1, y), (x, y+1), (x-1, y), (x, y-1) 이다. 단, 미로의 밖으로 이동 할 수는 없다.
 *
 * 벽은 평소에는 이동할 수 없지만, 알고스팟의 무기 AOJ를 이용해 벽을 부수어 버릴 수 있다. 벽을 부수면, 빈 방과 동일한 방으로 변한다.
 *
 * 만약 이 문제가 알고스팟에 있다면, 운영진들은 궁극의 무기 sudo를 이용해 벽을 한 번에 다 없애버릴 수 있지만, 안타깝게도 이 문제는 Baekjoon Online Judge에 수록되어 있기 때문에, sudo를 사용할 수 없다.
 *
 * 현재 (1, 1)에 있는 알고스팟 운영진이 (N, M)으로 이동하려면 벽을 최소 몇 개 부수어야 하는지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 미로의 크기를 나타내는 가로 크기 M, 세로 크기 N (1 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 미로의 상태를 나타내는 숫자 0과 1이 주어진다. 0은 빈 방을 의미하고, 1은 벽을 의미한다.
 *
 * (1, 1)과 (N, M)은 항상 뚫려있다.
 *
 * 출력
 * 첫째 줄에 알고스팟 운영진이 (N, M)으로 이동하기 위해 벽을 최소 몇 개 부수어야 하는지 출력한다.
 *
 * 예제 입력 1
 * 3 3
 * 011
 * 111
 * 110
 * 예제 출력 1
 * 3
 * 예제 입력 2
 * 4 2
 * 0001
 * 1000
 * 예제 출력 2
 * 0
 * 예제 입력 3
 * 6 6
 * 001111
 * 010000
 * 001111
 * 110001
 * 011010
 * 100010
 * 예제 출력 3
 * 2
 */

package gold;

import java.io.*;
import java.util.*;

public class P1261 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    private int[] dN = {0, 0, -1, 1};
    private int[] dM = {-1, 1, 0, 0};

    int N, M;
    private int[][] distance;
    private char[][] array;

    public static void main(String[] args) throws IOException {
        new P1261().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        array = new char[N][M];
        distance = new int[N][M];

        for (int i = 0; i < N; i++) {
            array[i] = br.readLine().toCharArray();
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        dijkstra();

        bw.write(distance[N - 1][M - 1] + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    private void dijkstra() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        distance[0][0] = 0;
        queue.offer(new Node(0, 0, 0));

        while (!queue.isEmpty()) {
            Node nextNode = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextN = nextNode.n + dN[i];
                int nextM = nextNode.m + dM[i];
                int nextBroken = nextNode.broken;

                if (nextN >= 0 && nextM >= 0 && nextN < N && nextM < M) {
                    if (array[nextN][nextM] == '1') {
                        nextBroken++;
                    }

                    if (distance[nextN][nextM] > nextBroken) {
                        distance[nextN][nextM] = nextBroken;
                        queue.offer(new Node(nextN, nextM, nextBroken));
                    }
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int n;
        int m;
        int broken;

        public Node(int n, int m, int count) {
            this.n = n;
            this.m = m;
            this.broken = count;
        }

        @Override
        public int compareTo(Node node) {
            return this.broken - node.broken;
        }
    }
}
