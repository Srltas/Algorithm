/**
 *
 * URL : https://www.acmicpc.net/problem/4963
 *
 * 섬의 개수
 *
 * 문제
 * 정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다. 섬의 개수를 세는 프로그램을 작성하시오.
 *
 *
 *
 * 한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다.
 *
 * 두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다. 지도는 바다로 둘러싸여 있으며, 지도 밖으로 나갈 수 없다.
 *
 * 입력
 * 입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. w와 h는 50보다 작거나 같은 양의 정수이다.
 *
 * 둘째 줄부터 h개 줄에는 지도가 주어진다. 1은 땅, 0은 바다이다.
 *
 * 입력의 마지막 줄에는 0이 두 개 주어진다.
 *
 * 출력
 * 각 테스트 케이스에 대해서, 섬의 개수를 출력한다.
 *
 * 예제 입력 1
 * 1 1
 * 0
 * 2 2
 * 0 1
 * 1 0
 * 3 2
 * 1 1 1
 * 1 1 1
 * 5 4
 * 1 0 1 0 0
 * 1 0 0 0 0
 * 1 0 1 0 1
 * 1 0 0 1 0
 * 5 4
 * 1 1 1 0 1
 * 1 0 1 0 1
 * 1 0 1 0 1
 * 1 0 1 1 1
 * 5 5
 * 1 0 1 0 1
 * 0 0 0 0 0
 * 1 0 1 0 1
 * 0 0 0 0 0
 * 1 0 1 0 1
 * 0 0
 * 예제 출력 1
 * 0
 * 1
 * 1
 * 3
 * 1
 * 9
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P4963 {

    static int w;
    static int h;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        boolean isLoop = true;

        while (isLoop) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                isLoop = false;
                continue;
            }

            map = new int[h][w];
            visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if(DFS(i, j)) {
                        count++;
                    };
                }
            }
            bw.write(count + System.lineSeparator());
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean DFS(int y, int x) {
        boolean isLand = false;

        if (y < h && y >= 0 && x < w && x >= 0) {
            if (!visited[y][x] && map[y][x] == 1) {
                isLand = true;
                visited[y][x] = true;

                DFS(y - 1, x - 1);
                DFS(y - 1, x);
                DFS(y - 1, x + 1);
                DFS(y, x - 1);
                DFS(y, x + 1);
                DFS(y + 1, x - 1);
                DFS(y + 1, x);
                DFS(y + 1, x + 1);
            }
        }
        return isLand;
    }
}
