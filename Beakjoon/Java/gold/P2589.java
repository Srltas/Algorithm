/**
 *
 * URL : https://www.acmicpc.net/problem/2589
 *
 * 보물섬
 *
 * 문제
 * 보물섬 지도를 발견한 후크 선장은 보물을 찾아나섰다. 보물섬 지도는 아래 그림과 같이 직사각형 모양이며 여러 칸으로 나뉘어져 있다. 각 칸은 육지(L)나 바다(W)로 표시되어 있다. 이 지도에서 이동은 상하좌우로 이웃한 육지로만 가능하며, 한 칸 이동하는데 한 시간이 걸린다. 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다. 육지를 나타내는 두 곳 사이를 최단 거리로 이동하려면 같은 곳을 두 번 이상 지나가거나, 멀리 돌아가서는 안 된다.
 *
 *
 *
 * 예를 들어 위와 같이 지도가 주어졌다면 보물은 아래 표시된 두 곳에 묻혀 있게 되고, 이 둘 사이의 최단 거리로 이동하는 시간은 8시간이 된다.
 *
 *
 *
 * 보물 지도가 주어질 때, 보물이 묻혀 있는 두 곳 간의 최단 거리로 이동하는 시간을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에는 보물 지도의 세로의 크기와 가로의 크기가 빈칸을 사이에 두고 주어진다. 이어 L과 W로 표시된 보물 지도가 아래의 예와 같이 주어지며, 각 문자 사이에는 빈 칸이 없다. 보물 지도의 가로, 세로의 크기는 각각 50이하이다.
 *
 * 출력
 * 첫째 줄에 보물이 묻혀 있는 두 곳 사이를 최단 거리로 이동하는 시간을 출력한다.
 *
 * 예제 입력 1
 * 5 7
 * WLLWWWL
 * LLLWLLL
 * LWLWLWW
 * LWLWLLL
 * WLLWLWW
 * 예제 출력 1
 * 8
 */

package gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2589 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] dL = {0, 0, -1, 1};
    int[] dW = {-1, 1, 0, 0};

    int L, W;
    int maxDistance = Integer.MIN_VALUE;
    char[][] map;

    public static void main(String[] args) throws IOException {
        new P2589().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[L][W];
        for (int i = 0; i < L; i++) {
            char[] array = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                map[i][j] = array[j];
            }
        }

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'L') {
                    maxDistance = Math.max(BFS(new Node(i, j)), maxDistance);
                }
            }
        }
        bw.write(maxDistance + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    private int BFS(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        boolean[][] visited = new boolean[L][W];
        visited[node.l][node.w] = true;

        int time = 0;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            while (queueSize-- > 0) {
                Node nextNode = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nextL = nextNode.l + dL[i];
                    int nextW = nextNode.w + dW[i];

                    if (nextL >= 0 && nextW >= 0 && nextL < L && nextW < W
                            && map[nextL][nextW] != 'W' && !visited[nextL][nextW]) {
                        queue.offer(new Node(nextL, nextW));
                        visited[nextL][nextW] = true;
                    }
                }
            }
            time++;
        }

        return time - 1;
    }

    static class Node {
        int l;
        int w;

        public Node (int l, int w) {
            this.l = l;
            this.w = w;
        }
    }
}
