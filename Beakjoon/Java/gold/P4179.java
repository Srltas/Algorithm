/**
 *
 * URL : https://www.acmicpc.net/problem/4179
 *
 * 불!
 *
 * 문제
 * 지훈이는 미로에서 일을 한다. 지훈이를 미로에서 탈출하도록 도와주자!
 *
 * 미로에서의 지훈이의 위치와 불이 붙은 위치를 감안해서 지훈이가 불에 타기전에 탈출할 수 있는지의 여부, 그리고 얼마나 빨리 탈출할 수 있는지를 결정해야한다.
 *
 * 지훈이와 불은 매 분마다 한칸씩 수평또는 수직으로(비스듬하게 이동하지 않는다) 이동한다.
 *
 * 불은 각 지점에서 네 방향으로 확산된다.
 *
 * 지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있다.
 *
 * 지훈이와 불은 벽이 있는 공간은 통과하지 못한다.
 *
 * 입력
 * 입력의 첫째 줄에는 공백으로 구분된 두 정수 R과 C가 주어진다. 단, 1 ≤ R, C ≤ 1000 이다. R은 미로 행의 개수, C는 열의 개수이다.
 *
 * 다음 입력으로 R줄동안 각각의 미로 행이 주어진다.
 *
 * 각각의 문자들은 다음을 뜻한다.
 *
 * #: 벽
 * .: 지나갈 수 있는 공간
 * J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간)
 * F: 불이 난 공간
 * J는 입력에서 하나만 주어진다.
 *
 * 출력
 * 지훈이가 불이 도달하기 전에 미로를 탈출 할 수 없는 경우 IMPOSSIBLE 을 출력한다.
 *
 * 지훈이가 미로를 탈출할 수 있는 경우에는 가장 빠른 탈출시간을 출력한다.
 *
 * 예제 입력 1
 * 4 4
 * ####
 * #JF#
 * #..#
 * #..#
 * 예제 출력 1
 * 3
 */

package gold;

import java.io.*;
import java.util.*;

public class P4179 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] dR = {0, 0, -1, 1};
    int[] dC = {-1 ,1, 0, 0};

    int R, C;
    char[][] maze;
    int time = 1;

    public static void main(String[] args) throws IOException {
        new P4179().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        maze = new char[R][C];
        Node startPoint = new Node();
        List<Node> fireList = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            char[] array = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                maze[i][j] = array[j];

                if (maze[i][j] == 'J') {
                    startPoint.init(i, j);
                } else if (maze[i][j] == 'F') {
                    fireList.add(new Node(i, j));
                }
            }
        }

        bw.write((BFS(startPoint, fireList) ? time : "IMPOSSIBLE") + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    private boolean BFS(Node startPoint, List<Node> fireList) {
        boolean[][] visited = new boolean[R][C];
        boolean[][] fireVisited = new boolean[R][C];

        Queue<Node> fireQueue = new LinkedList<>();
        for (Node node : fireList) {
            fireQueue.offer(node);
            fireVisited[node.r][node.c] = true;
        }

        Queue<Node> jiHoonQueue = new LinkedList<>();
        jiHoonQueue.offer(startPoint);
        visited[startPoint.r][startPoint.c] = true;

        while (!jiHoonQueue.isEmpty()) {
            int queueSize = jiHoonQueue.size();
            while (queueSize-- > 0) {
                Node nextNode = jiHoonQueue.poll();

                if (maze[nextNode.r][nextNode.c] == 'F') {
                    continue;
                }

                if (nextNode.r == 0 || nextNode.c == 0 || nextNode.r == R - 1 || nextNode.c == C - 1) {
                    return true;
                }

                for (int i = 0; i < 4; i++) {
                    int nextR = nextNode.r + dR[i];
                    int nextC = nextNode.c + dC[i];

                    if (!visited[nextR][nextC] && maze[nextR][nextC] != '#' && maze[nextR][nextC] != 'F') {
                        jiHoonQueue.offer(new Node(nextR, nextC));
                        visited[nextR][nextC] = true;
                        maze[nextR][nextC] = 'J';
                    }
                }

            }

            int fireQueueSize = fireQueue.size();
            while (fireQueueSize-- > 0) {
                Node nextNode = fireQueue.poll();
                for (int i = 0; i < 4; i++) {
                    int nextR = nextNode.r + dR[i];
                    int nextC = nextNode.c + dC[i];

                    if (nextR >= 0 && nextC >= 0 && nextR < R && nextC < C
                            && !fireVisited[nextR][nextC] && maze[nextR][nextC] != '#') {
                        fireQueue.offer(new Node(nextR, nextC));
                        maze[nextR][nextC] = 'F';
                        fireVisited[nextR][nextC] = true;
                    }
                }
            }
            time++;
        }



        return false;
    }


    static class Node {
        int r;
        int c;

        public Node() {}

        public Node (int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void init(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
