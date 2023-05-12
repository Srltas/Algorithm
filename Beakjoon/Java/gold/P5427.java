/**
 *
 * URL : https://www.acmicpc.net/problem/5427
 *
 * 불
 *
 * 문제
 * 상근이는 빈 공간과 벽으로 이루어진 건물에 갇혀있다. 건물의 일부에는 불이 났고, 상근이는 출구를 향해 뛰고 있다.
 *
 * 매 초마다, 불은 동서남북 방향으로 인접한 빈 공간으로 퍼져나간다. 벽에는 불이 붙지 않는다. 상근이는 동서남북 인접한 칸으로 이동할 수 있으며, 1초가 걸린다. 상근이는 벽을 통과할 수 없고, 불이 옮겨진 칸 또는 이제 불이 붙으려는 칸으로 이동할 수 없다. 상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로 이동할 수 있다.
 *
 * 빌딩의 지도가 주어졌을 때, 얼마나 빨리 빌딩을 탈출할 수 있는지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스는 최대 100개이다.
 *
 * 각 테스트 케이스의 첫째 줄에는 빌딩 지도의 너비와 높이 w와 h가 주어진다. (1 ≤ w,h ≤ 1000)
 *
 * 다음 h개 줄에는 w개의 문자, 빌딩의 지도가 주어진다.
 *
 * '.': 빈 공간
 * '#': 벽
 * '@': 상근이의 시작 위치
 * '*': 불
 * 각 지도에 @의 개수는 하나이다.
 *
 * 출력
 * 각 테스트 케이스마다 빌딩을 탈출하는데 가장 빠른 시간을 출력한다. 빌딩을 탈출할 수 없는 경우에는 "IMPOSSIBLE"을 출력한다.
 *
 * 예제 입력 1
 * 5
 * 4 3
 * ####
 * #*@.
 * ####
 * 7 6
 * ###.###
 * #*#.#*#
 * #.....#
 * #.....#
 * #..@..#
 * #######
 * 7 4
 * ###.###
 * #....*#
 * #@....#
 * .######
 * 5 5
 * .....
 * .***.
 * .*@*.
 * .***.
 * .....
 * 3 3
 * ###
 * #@#
 * ###
 * 예제 출력 1
 * 2
 * 5
 * IMPOSSIBLE
 * IMPOSSIBLE
 * IMPOSSIBLE
 */

package gold;

import java.io.*;
import java.util.*;

public class P5427 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int dh[] = {0, 0, -1, 1};
    int dw[] = {-1, 1, 0, 0};

    private int w, h;
    private int time;
    private char[][] building;

    public static void main(String[] args) throws IOException {
        new P5427().solution();
    }

    public void solution() throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            building = new char[h][w];

            Node startPoint = new Node();
            List<Node> fireList = new ArrayList<>();
            for (int i = 0; i < h; i++) {
                char[] floor = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    building[i][j] = floor[j];

                    if (building[i][j] == '@') {
                        startPoint.init(i, j);
                    }

                    if (building[i][j] == '*') {
                        fireList.add(new Node(i, j));
                    }
                }
            }

            time = 1;
            bw.write((BFS(startPoint, fireList) ? time : "IMPOSSIBLE") + System.lineSeparator());
        }
        bw.flush();

        bw.close();
        br.close();
    }

    private boolean BFS(Node startPoint, List<Node> fireList) {
        boolean[][] sangVisited = new boolean[h][w];
        boolean[][] fireVisited = new boolean[h][w];

        Queue<Node> sangQueue = new LinkedList<>();
        sangQueue.offer(startPoint);
        sangVisited[startPoint.h][startPoint.w] = true;

        Queue<Node> fireQueue = new LinkedList<>();
        for (Node node : fireList) {
            fireQueue.offer(new Node(node.h, node.w));
            fireVisited[node.h][node.w] = true;
        }

        while (!sangQueue.isEmpty()) {
            int sangQueueSize = sangQueue.size();
            while (sangQueueSize-- > 0) {
                Node nextNode = sangQueue.poll();

                // 불로 지워진 자리
                if (building[nextNode.h][nextNode.w] == '*') {
                    continue;
                }

                if (nextNode.h == 0 || nextNode.w == 0 || nextNode.h == h - 1 || nextNode.w == w - 1) {
                    return true;
                }

                for (int i = 0; i < 4; i++) {
                    int newH = nextNode.h + dh[i];
                    int newW = nextNode.w + dw[i];

                    if (building[newH][newW] != '#' && building[newH][newW] != '*' && !sangVisited[newH][newW]) {
                        sangVisited[newH][newW] = true;
                        building[newH][newW] = '@';
                        sangQueue.offer(new Node(newH, newW));
                    }
                }
            }

            int fireQueueSize = fireQueue.size();
            while (fireQueueSize-- > 0) {
                Node nextNode = fireQueue.poll();
                for (int i = 0; i < 4; i++) {
                    int newH = nextNode.h + dh[i];
                    int newW = nextNode.w + dw[i];

                    if (newH >= 0 && newW >= 0 && newH < h && newW < w && building[newH][newW] != '#' && !fireVisited[newH][newW]) {
                        fireVisited[newH][newW] = true;
                        building[newH][newW] = '*';
                        fireQueue.offer(new Node(newH, newW));
                    }
                }
            }
            time++;
        }

        return false;
    }

    static class Node {
        int h;
        int w;

        public Node() {}

        public Node(int h, int w) {
            this.h = h;
            this.w = w;
        }

        public void init(int h, int w) {
            this.h = h;
            this.w = w;
        }
    }
}
