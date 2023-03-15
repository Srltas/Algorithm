/**
 *
 * URL : https://www.acmicpc.net/problem/7562
 *
 * 나이트의 이동
 *
 * 문제
 * 체스판 위에 한 나이트가 놓여져 있다. 나이트가 한 번에 이동할 수 있는 칸은 아래 그림에 나와있다. 나이트가 이동하려고 하는 칸이 주어진다. 나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?
 *
 *
 *
 * 입력
 * 입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.
 *
 * 각 테스트 케이스는 세 줄로 이루어져 있다. 첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다. 체스판의 크기는 l × l이다. 체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}로 나타낼 수 있다. 둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.
 *
 * 출력
 * 각 테스트 케이스마다 나이트가 최소 몇 번만에 이동할 수 있는지 출력한다.
 *
 * 예제 입력 1
 * 3
 * 8
 * 0 0
 * 7 0
 * 100
 * 0 0
 * 30 50
 * 10
 * 1 1
 * 1 1
 * 예제 출력 1
 * 5
 * 28
 * 0
 */

package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P7562 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
    int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};

    int N;
    int[][] board;
    boolean[][] visited;
    Point[] points;

    public static void main(String[] args) throws IOException {
        new P7562().solution();
    }

    public void solution() throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            visited = new boolean[N][N];

            points = new Point[2];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
            }
            bw.write(BFS() + System.lineSeparator());
        }
        bw.flush();

        bw.close();
        br.close();
    }

    public int BFS() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(points[0]);

        visited[points[0].x][points[0].y] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            if (p.x == points[1].x && p.y == points[1].y) {
                return p.cnt;
            }

            for (int i = 0; i < 8; i++) {
                int x = p.x + dx[i];
                int y = p.y + dy[i];

                if (x < 0 || y < 0 || x >= N || y >= N) {
                    continue;
                }

                if (!visited[x][y]) {
                    visited[x][y] = true;
                    queue.offer(new Point(x, y, p.cnt + 1));
                }
            }
        }

        return -1;
    }

    static class Point {
        int x, y, cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
