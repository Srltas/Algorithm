/**
 *
 * URL : https://www.acmicpc.net/problem/13549
 *
 * 숨바꼭질 3
 *
 * 문제
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다.
 *
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 *
 * 출력
 * 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 *
 * 예제 입력 1
 * 5 17
 * 예제 출력 1
 * 2
 */

package gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P13549 {
    final int MAX_X = 100000;
    final int MIN_X = 0;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    boolean[] visited = new boolean[MAX_X + 1];

    int result = MAX_X;
    int N;
    int K;

    public static void main(String[] args) throws IOException {
        new P13549().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        BFS(N);

        bw.write(result + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    public void BFS(int start) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited[node.x] = true;

            if (node.x == K) {
                result = Math.min(result, node.time);
            }
            if (node.x * 2 <= MAX_X && !visited[node.x * 2]) {
                queue.offer(new Node(node.x * 2, node.time));
            }
            if (node.x + 1 <= MAX_X && !visited[node.x + 1]) {
                queue.offer(new Node(node.x + 1, node.time + 1));
            }
            if (node.x - 1 >= MIN_X && !visited[node.x - 1]) {
                queue.offer(new Node(node.x - 1, node.time + 1));
            }
        }
    }

    class Node {
        int x;
        int time;

        public Node(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}
