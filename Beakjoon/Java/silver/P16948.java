/**
 * 문제
 * 게임을 좋아하는 큐브러버는 체스에서 사용할 새로운 말 "데스 나이트"를 만들었다. 데스 나이트가 있는 곳이 (r, c)라면, (r-2, c-1), (r-2, c+1), (r, c-2), (r, c+2), (r+2, c-1), (r+2, c+1)로 이동할 수 있다.
 *
 * 크기가 N×N인 체스판과 두 칸 (r1, c1), (r2, c2)가 주어진다. 데스 나이트가 (r1, c1)에서 (r2, c2)로 이동하는 최소 이동 횟수를 구해보자. 체스판의 행과 열은 0번부터 시작한다.
 *
 * 데스 나이트는 체스판 밖으로 벗어날 수 없다.
 *
 * 입력
 * 첫째 줄에 체스판의 크기 N(5 ≤ N ≤ 200)이 주어진다. 둘째 줄에 r1, c1, r2, c2가 주어진다.
 *
 * 출력
 * 첫째 줄에 데스 나이트가 (r1, c1)에서 (r2, c2)로 이동하는 최소 이동 횟수를 출력한다. 이동할 수 없는 경우에는 -1을 출력한다.
 *
 * 예제 입력 1
 * 7
 * 6 6 0 1
 * 예제 출력 1
 * 4
 * 예제 입력 2
 * 6
 * 5 1 0 5
 * 예제 출력 2
 * -1
 * 예제 입력 3
 * 7
 * 0 3 4 3
 * 예제 출력 3
 * 2
 */

package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P16948 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] dr = {-2, -2, 0, 0, 2, 2};
    int[] dc = {-1, 1, -2, 2, -1, 1};

    int N;
    boolean[][] visited;

    public static void main(String[] args) throws IOException {
        new P16948().solution();
    }

    public void solution() throws IOException {
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];
        st = new StringTokenizer(br.readLine());

        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());

        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        bw.write(BFS(new Node(r1, c1), new Node(r2, c2)) + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    public int BFS(Node node, Node targetNode) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        visited[node.r][node.c] = true;

        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node nextNode = queue.poll();
                for (int i = 0; i < 6; i++) {
                    int r = nextNode.r + dr[i];
                    int c = nextNode.c + dc[i];

                    if (r >= 0 && c >= 0 && r < N && c < N && !visited[r][c]) {
                        if (targetNode.r == r && targetNode.c == c) {
                            return count;
                        }
                        queue.offer(new Node(r, c));
                        visited[r][c] = true;
                    }
                }
            }
            count++;
        }

        return -1;
    }

    static class Node {
        int r;
        int c;

        public Node (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
