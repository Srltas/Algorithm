/**
 *
 * URL : https://www.acmicpc.net/problem/2210
 *
 * 숫자판 점프
 * 
 * 문제
 * 5×5 크기의 숫자판이 있다. 각각의 칸에는 숫자(digit, 0부터 9까지)가 적혀 있다. 이 숫자판의 임의의 위치에서 시작해서, 인접해 있는 네 방향으로 다섯 번 이동하면서, 각 칸에 적혀있는 숫자를 차례로 붙이면 6자리의 수가 된다. 이동을 할 때에는 한 번 거쳤던 칸을 다시 거쳐도 되며, 0으로 시작하는 000123과 같은 수로 만들 수 있다.
 *
 * 숫자판이 주어졌을 때, 만들 수 있는 서로 다른 여섯 자리의 수들의 개수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 다섯 개의 줄에 다섯 개의 정수로 숫자판이 주어진다.
 *
 * 출력
 * 첫째 줄에 만들 수 있는 수들의 개수를 출력한다.
 *
 * 예제 입력 1
 * 1 1 1 1 1
 * 1 1 1 1 1
 * 1 1 1 1 1
 * 1 1 1 2 1
 * 1 1 1 1 1
 * 예제 출력 1
 * 15
 */

package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P2210 {

    static Set<String> set = new HashSet<>();
    static String[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new String[5][5];

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[i][j] = st.nextToken();
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                DFS(i, j, board[i][j]);
            }
        }

        System.out.println(set.stream().count());
    }

    static void DFS(int x, int y, String s) {
        if (s.length() == 6) {
            set.add(s);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 5 && nx >= 0 && ny < 5 && ny >= 0) {
                DFS(nx, ny, s + board[nx][ny]);
            }
        }
    }
}
