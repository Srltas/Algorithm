/**
 *
 * URL : https://www.acmicpc.net/problem/14940
 *
 * 쉬운 최단거리
 *
 * 문제
 * 지도가 주어지면 모든 지점에 대해서 목표지점까지의 거리를 구하여라.
 *
 * 문제를 쉽게 만들기 위해 오직 가로와 세로로만 움직일 수 있다고 하자.
 *
 * 입력
 * 지도의 크기 n과 m이 주어진다. n은 세로의 크기, m은 가로의 크기다.(2 ≤ n ≤ 1000, 2 ≤ m ≤ 1000)
 *
 * 다음 n개의 줄에 m개의 숫자가 주어진다. 0은 갈 수 없는 땅이고 1은 갈 수 있는 땅, 2는 목표지점이다. 입력에서 2는 단 한개이다.
 *
 * 출력
 * 각 지점에서 목표지점까지의 거리를 출력한다. 원래 갈 수 없는 땅인 위치는 0을 출력하고, 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1을 출력한다.
 *
 * 예제 입력 1
 * 15 15
 * 2 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 0 0 0 0 1
 * 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 0 1 0 0 0
 * 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1
 * 예제 출력 1
 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
 * 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17
 * 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18
 * 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
 * 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
 * 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21
 * 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22
 * 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
 * 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24
 * 11 12 13 14 15 16 17 18 19 20 0 0 0 0 25
 * 12 13 14 15 16 17 18 19 20 21 0 29 28 27 26
 * 13 14 15 16 17 18 19 20 21 22 0 30 0 0 0
 * 14 15 16 17 18 19 20 21 22 23 0 31 32 33 34
 */

package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P14940 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] dn = {-1, 1, 0, 0};
    int[] dm = {0, 0, -1, 1};

    int N, M;
    int[][] array;
    boolean[][] visited;

    public static void main(String[] args) throws IOException {
        new P14940().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        array = new int[N][M];
        visited = new boolean[N][M];
        Node startNode = new Node();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
                if (array[i][j] == 2) {
                    startNode.setLocation(i, j);
                }
            }
        }

        BFS(startNode);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && array[i][j] == 1) {
                    array[i][j] = -1;
                }

                bw.write(array[i][j] + " ");
            }
            bw.write(System.lineSeparator());
        }
        bw.flush();

        bw.close();
        br.close();
    }

    private void BFS(Node startNode) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(startNode);
        visited[startNode.n][startNode.m] = true;
        array[startNode.n][startNode.m] = 0;

        int distance = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node nextNode = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int n = nextNode.n + dn[i];
                    int m = nextNode.m + dm[i];

                    if (n >= 0 && m >= 0 && n < N && m < M && !visited[n][m] && array[n][m] != 0) {
                        array[n][m] = distance;
                        queue.offer(new Node(n, m));
                        visited[n][m] = true;
                    }
                }
            }
            distance++;
        }
    }

    static class Node {
        int n;
        int m;

        public Node() {}

        public Node(int n, int m) {
            this.n = n;
            this.m = m;
        }

        public void setLocation(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }
}
