/**
 *
 * URL : https://www.acmicpc.net/problem/13565
 *
 * 침투
 *
 * 문제
 * 인제대학교 생화학연구실에 재직중인 석교수는 전류가 침투(percolate) 할 수 있는 섬유 물질을 개발하고 있다. 이 섬유 물질은 2차원 M × N 격자로 표현될 수 있다. 편의상 2차원 격자의 위쪽을 바깥쪽(outer side), 아래쪽을 안쪽(inner side)라고 생각하기로 한다. 또한 각 격자는 검은색 아니면 흰색인데, 검은색은 전류를 차단하는 물질임을 뜻하고 흰색은 전류가 통할 수 있는 물질임을 뜻한다. 전류는 섬유 물질의 가장 바깥쪽 흰색 격자들에 공급되고, 이후에는 상하좌우로 인접한 흰색 격자들로 전달될 수 있다.
 *
 * 김 교수가 개발한 섬유 물질을 나타내는 정보가 2차원 격자 형태로 주어질 때, 바깥쪽에서 흘려 준 전류가 안쪽까지 침투될 수 있는지 아닌지를 판단하는 프로그램을 작성하시오.
 *
 *
 * (a) The current percolates.	(b) The current does not percolate.
 * 예를 들어, Figure 1(a) 에서는 바깥쪽에서 공급된 전류가 안쪽까지 침투하지만, Figure 1(b)에서는 그렇지 못한다.
 *
 * 입력
 * 첫째 줄에는 격자의 크기를 나타내는  M (2 ≤ M ≤ 1,000) 과 N (2 ≤ N ≤ 1,000) 이 주어진다. M줄에 걸쳐서, N개의 0 또는 1 이 공백 없이 주어진다. 0은 전류가 잘 통하는 흰색, 1은 전류가 통하지 않는 검은색 격자임을 뜻한다.
 *
 * 출력
 * 바깥에서 흘려준 전류가 안쪽까지 잘 전달되면 YES를 출력한다.
 *
 * 그렇지 않으면 NO를 출력한다.
 *
 * 예제 입력 1
 * 5 6
 * 010101
 * 010000
 * 011101
 * 100011
 * 001011
 * 예제 출력 1
 * NO
 * 예제 입력 2
 * 8 8
 * 11000111
 * 01100000
 * 00011001
 * 11001000
 * 10001001
 * 10111100
 * 01010000
 * 00001011
 * 예제 출력 2
 * YES
 */

package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P13565 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] dm = {1, -1, 0, 0};
    int[] dn = {0, 0, -1, 1};

    int M, N;
    boolean[][] array;

    public static void main(String[] args) throws IOException {
        new P13565().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        array = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                array[i][j] = row[j] == '0';
            }
        }

        boolean isThrough = false;
        for (int i = 0; i < N; i++) {
            if (BFS(new Node(0, i))) {
                isThrough = true;
                break;
            }
        }
        bw.write(isThrough ? "YES" : "NO");
        bw.flush();

        bw.close();
        br.close();
    }

    public boolean BFS(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        array[node.m][node.n] = true;

        while (!queue.isEmpty()) {
            Node nextNode = queue.poll();
            for (int i = 0; i < 4; i++) {
                int m = nextNode.m + dm[i];
                int n = nextNode.n + dn[i];

                if (m >= 0 && n >= 0 && m < M && n < N && array[m][n]) {
                    if (m == M - 1) {
                        return true;
                    }
                    queue.offer(new Node(m, n));
                    array[m][n] = false;
                }
            }
        }

        return false;
    }

    static class Node {
        int m;
        int n;

        public Node (int m, int n) {
            this.m = m;
            this.n = n;
        }
    }
}
