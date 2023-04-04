/**
 *
 * URL : https://www.acmicpc.net/problem/14716
 *
 * 현수막
 *
 * 문제
 * ANT가 처음 알고리즘 대회를 개최하게 되면서 현수막을 내걸었다.
 *
 *
 *
 * 저번 학기 영상처리 수업을 듣고 배웠던 지식을 최대한 응용 해보고 싶은 혁진이는 이 현수막에서 글자가 몇 개인지 알아보는 프로그램을 만들려 한다.
 *
 * 혁진이는 우선 현수막에서 글자인 부분은 1, 글자가 아닌 부분은 0으로 바꾸는 필터를 적용하여 값을 만드는데 성공했다.
 *
 * 그런데 혁진이는 이 값을 바탕으로 글자인 부분 1이 상, 하, 좌, 우, 대각선으로 인접하여 서로 연결되어 있다면 한 개의 글자라고 생각만 하였다.
 *
 * 혁진이가 필터를 적용하여 만든 값이 입력으로 주어졌을 때, 혁진이의 생각대로 프로그램을 구현하면 글자의 개수가 몇 개인지 출력하여라.
 *
 * 입력
 * 첫 번째 줄에는 현수막의 크기인 M와 N가 주어진다. (1 ≤ M, N ≤ 250)
 *
 * 두 번째 줄부터 M+1 번째 줄까지 현수막의 정보가 1과 0으로 주어지며, 1과 0을 제외한 입력은 주어지지 않는다.
 *
 * 출력
 * 혁진이의 생각대로 프로그램을 구현했을 때, 현수막에서 글자의 개수가 몇 개인지 출력하여라.
 *
 * 예제 입력 1
 * 8 19
 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 1 0 0 0 1 0 0 0 1 0 1 1 1 1 1 0
 * 0 0 1 0 1 0 0 1 1 0 0 1 0 0 0 1 0 0 0
 * 0 1 0 0 0 1 0 1 0 1 0 1 0 0 0 1 0 0 0
 * 0 1 1 1 1 1 0 1 0 1 0 1 0 0 0 1 0 0 0
 * 0 1 0 0 0 1 0 1 0 0 1 1 0 0 0 1 0 0 0
 * 0 1 0 0 0 1 0 1 0 0 0 1 0 0 0 1 0 0 0
 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 예제 출력 1
 * 3
 */

package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P14716 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] dm = {0, 0, -1, 1, -1, -1, 1, 1};
    int[] dn = {-1, 1, 0, 0, -1, 1, -1, 1};

    int M, N;
    int[][] array;
    boolean[][] visited;

    public static void main(String[] args) throws IOException {
        new P14716().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        array = new int[M][N];
        visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (array[i][j] == 1) {
                    BFS(new Node(i, j));
                    count++;
                }
            }
        }
        bw.write(count + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    public void BFS(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        visited[node.m][node.n] = true;

        while (!queue.isEmpty()) {
            Node nextNode = queue.poll();
            for (int i = 0; i < 8; i++) {
                int m = nextNode.m + dm[i];
                int n = nextNode.n + dn[i];

                if (m >= 0 && n >= 0 && m < M && n < N && !visited[m][n]) {
                    visited[m][n] = true;
                    if (array[m][n] == 1) {
                        queue.offer(new Node(m, n));
                        array[m][n] = 0;
                    }
                }
            }
        }
    }

    static class Node {
        int m;
        int n;

        public Node(int m, int n) {
            this.m = m;
            this.n = n;
        }
    }
}
