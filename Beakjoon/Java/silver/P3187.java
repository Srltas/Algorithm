/**
 *
 * URL : https://www.acmicpc.net/problem/3187
 *
 * 양치기 꿍
 *
 * 문제
 * 양치기 꿍은 맨날 늑대가 나타났다고 마을 사람들을 속였지만 이젠 더이상 마을 사람들이 속지 않는다. 화가 난 꿍은 복수심에 불타 아예 늑대들을 양들이 있는 울타리안에 마구 집어넣어 양들을 잡아먹게 했다.
 *
 * 하지만 양들은 보통 양들이 아니다. 같은 울타리 영역 안의 양들의 숫자가 늑대의 숫자보다 더 많을 경우 늑대가 전부 잡아먹힌다. 물론 그 외의 경우는 양이 전부 잡아먹히겠지만 말이다.
 *
 * 꿍은 워낙 똑똑했기 때문에 이들의 결과는 이미 알고있다. 만약 빈 공간을 '.'(점)으로 나타내고 울타리를 '#', 늑대를 'v', 양을 'k'라고 나타낸다면 여러분은 몇 마리의 양과 늑대가 살아남을지 계산할 수 있겠는가?
 *
 * 단, 울타리로 막히지 않은 영역에는 양과 늑대가 없으며 양과 늑대는 대각선으로 이동할 수 없다.
 *
 * 입력
 * 입력의 첫 번째 줄에는 각각 영역의 세로와 가로의 길이를 나타내는 두 개의 정수 R, C (3 ≤ R, C ≤ 250)가 주어진다.
 *
 * 다음 각 R줄에는 C개의 문자가 주어지며 이들은 위에서 설명한 기호들이다.
 *
 * 출력
 * 살아남게 되는 양과 늑대의 수를 각각 순서대로 출력한다.
 *
 * 예제 입력 1
 * 6 6
 * ...#..
 * .##v#.
 * #v.#.#
 * #.k#.#
 * .###.#
 * ...###
 * 예제 출력 1
 * 0 2
 * 예제 입력 2
 * 8 8
 * .######.
 * #..k...#
 * #.####.#
 * #.#v.#.#
 * #.#.k#k#
 * #k.##..#
 * #.v..v.#
 * .######.
 * 예제 출력 2
 * 3 1
 * 예제 입력 3
 * 9 12
 * .###.#####..
 * #.kk#...#v#.
 * #..k#.#.#.#.
 * #..##k#...#.
 * #.#v#k###.#.
 * #..#v#....#.
 * #...v#v####.
 * .####.#vv.k#
 * .......####.
 * 예제 출력 3
 * 3 5
 */

package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P3187 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    int R, C;
    int totalV, totalK;
    char[][] array;
    boolean[][] visited;

    public static void main(String[] args) throws IOException {
        new P3187().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        array = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            array[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j]) {
                    BFS(new Node(i, j));
                }
            }
        }
        bw.write(totalK + " " + totalV);
        bw.flush();

        bw.close();
        br.close();
    }

    public void BFS(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        visited[node.r][node.c] = true;

        Area area = new Area();
        checkSheepOrWolf(node.r, node.c, area);

        while (!queue.isEmpty()) {
            Node nextNode = queue.poll();

            for (int i = 0; i < 4; i++) {
                int r = nextNode.r + dr[i];
                int c = nextNode.c + dc[i];

                if (r >= 0 && c >= 0 && r < R && c < C && !visited[r][c]) {
                    visited[r][c] = true;

                    if (array[r][c] != '#') {
                        checkSheepOrWolf(r, c, area);
                        queue.offer(new Node(r, c));
                    }
                }
            }
        }

        if (area.v >= area.k) {
            totalV += area.v;
        } else {
            totalK += area.k;
        }
    }

    private void checkSheepOrWolf(int r, int c, Area area) {
        if (array[r][c] == 'k') {
            area.addSheep();
        } else if (array[r][c] == 'v') {
            area.addWolf();
        }
    }

    static class Area {
        int v;
        int k;

        public void addSheep() {
            k++;
        }

        public void addWolf() {
            v++;
        }
    }

    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
