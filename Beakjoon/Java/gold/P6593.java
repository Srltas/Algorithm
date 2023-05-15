/**
 *
 * URL : https://www.acmicpc.net/problem/6593
 *
 * 상범 빌딩
 *
 * 문제
 * 당신은 상범 빌딩에 갇히고 말았다. 여기서 탈출하는 가장 빠른 길은 무엇일까? 상범 빌딩은 각 변의 길이가 1인 정육면체(단위 정육면체)로 이루어져있다. 각 정육면체는 금으로 이루어져 있어 지나갈 수 없거나, 비어있어서 지나갈 수 있게 되어있다. 당신은 각 칸에서 인접한 6개의 칸(동,서,남,북,상,하)으로 1분의 시간을 들여 이동할 수 있다. 즉, 대각선으로 이동하는 것은 불가능하다. 그리고 상범 빌딩의 바깥면도 모두 금으로 막혀있어 출구를 통해서만 탈출할 수 있다.
 *
 * 당신은 상범 빌딩을 탈출할 수 있을까? 만약 그렇다면 얼마나 걸릴까?
 *
 * 입력
 * 입력은 여러 개의 테스트 케이스로 이루어지며, 각 테스트 케이스는 세 개의 정수 L, R, C로 시작한다. L(1 ≤ L ≤ 30)은 상범 빌딩의 층 수이다. R(1 ≤ R ≤ 30)과 C(1 ≤ C ≤ 30)는 상범 빌딩의 한 층의 행과 열의 개수를 나타낸다.
 *
 * 그 다음 각 줄이 C개의 문자로 이루어진 R개의 행이 L번 주어진다. 각 문자는 상범 빌딩의 한 칸을 나타낸다. 금으로 막혀있어 지나갈 수 없는 칸은 '#'으로 표현되고, 비어있는 칸은 '.'으로 표현된다. 당신의 시작 지점은 'S'로 표현되고, 탈출할 수 있는 출구는 'E'로 표현된다. 각 층 사이에는 빈 줄이 있으며, 입력의 끝은 L, R, C가 모두 0으로 표현된다. 시작 지점과 출구는 항상 하나만 있다.
 *
 * 출력
 * 각 빌딩에 대해 한 줄씩 답을 출력한다. 만약 당신이 탈출할 수 있다면 다음과 같이 출력한다.
 *
 * Escaped in x minute(s).
 * 여기서 x는 상범 빌딩을 탈출하는 데에 필요한 최단 시간이다.
 *
 * 만일 탈출이 불가능하다면, 다음과 같이 출력한다.
 *
 * Trapped!
 * 예제 입력 1
 * 3 4 5
 * S....
 * .###.
 * .##..
 * ###.#
 *
 * #####
 * #####
 * ##.##
 * ##...
 *
 * #####
 * #####
 * #.###
 * ####E
 *
 * 1 3 3
 * S##
 * #E#
 * ###
 *
 * 0 0 0
 * 예제 출력 1
 * Escaped in 11 minute(s).
 * Trapped!
 */

package gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P6593 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] dC = {-1, 1, 0, 0, 0, 0};
    int[] dR = {0, 0, -1, 1, 0, 0};
    int[] dL = {0, 0, 0, 0, -1, 1};

    int C, R, L;
    char[][][] building;
    int time;

    public static void main(String[] args) throws IOException {
        new P6593().solution();
    }

    public void solution() throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            if (C == 0 && R == 0 && L == 0) {
                break;
            }

            Node startPoint = new Node();
            Node endPoint = new Node();
            building = new char[C][R][L];
            for (int k = 0; k < C; k++) {
                for (int i = 0; i < R + 1; i++) {
                    char[] array = br.readLine().toCharArray();
                    if (array.length == 0) {
                        continue;
                    }

                    for (int j = 0; j < L; j++) {
                        building[k][i][j] = array[j];
                        if (building[k][i][j] == 'S') {
                            startPoint.init(k, i, j);
                        } else if (building[k][i][j] == 'E') {
                            endPoint.init(k, i, j);
                        }
                    }
                }
            }
            bw.write((BFS(startPoint, endPoint) ? "Escaped in " + (time + 1) + " minute(s)." : "Trapped!") + System.lineSeparator());
        }
        bw.flush();


        bw.close();
        br.close();
    }

    private boolean BFS(Node startPoint, Node endPoint) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(startPoint);

        boolean[][][] visited = new boolean[C][R][L];
        visited[startPoint.c][startPoint.r][startPoint.l] = true;

        time = 0;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            while (queueSize-- > 0) {
                Node nextNode = queue.poll();

                for (int i = 0; i < 6; i++) {
                    int nextC = nextNode.c + dC[i];
                    int nextR = nextNode.r + dR[i];
                    int nextL = nextNode.l + dL[i];

                    if (nextC >= 0 && nextR >= 0 && nextL >= 0
                            && nextC < C && nextR < R && nextL < L
                            && building[nextC][nextR][nextL] != '#'
                            && !visited[nextC][nextR][nextL]) {
                        if (nextC == endPoint.c && nextR == endPoint.r && nextL == endPoint.l) {
                            return true;
                        }

                        queue.offer(new Node(nextC, nextR, nextL));
                        visited[nextC][nextR][nextL] = true;
                    }
                }
            }
            time++;
        }

        return false;
    }

    static class Node {
        int c;
        int r;
        int l;

        public Node() {}

        public Node(int c, int r, int l) {
            this.c = c;
            this.r = r;
            this.l = l;
        }

        public void init(int c, int r, int l) {
            this.c = c;
            this.r = r;
            this.l = l;
        }
    }
}
