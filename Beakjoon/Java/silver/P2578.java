/**
 *
 * URL : https://www.acmicpc.net/problem/2578
 *
 * 빙고
 *
 * 문제
 * 빙고 게임은 다음과 같은 방식으로 이루어진다.
 *
 * 먼저 아래와 같이 25개의 칸으로 이루어진 빙고판에 1부터 25까지 자연수를 한 칸에 하나씩 쓴다
 *
 *
 *
 * 다음은 사회자가 부르는 수를 차례로 지워나간다. 예를 들어 5, 10, 7이 불렸다면 이 세 수를 지운 뒤 빙고판의 모습은 다음과 같다.
 *
 *
 *
 * 차례로 수를 지워가다가 같은 가로줄, 세로줄 또는 대각선 위에 있는 5개의 모든 수가 지워지는 경우 그 줄에 선을 긋는다.
 *
 *
 *
 * 이러한 선이 세 개 이상 그어지는 순간 "빙고"라고 외치는데, 가장 먼저 외치는 사람이 게임의 승자가 된다.
 *
 *
 *
 * 철수는 친구들과 빙고 게임을 하고 있다. 철수가 빙고판에 쓴 수들과 사회자가 부르는 수의 순서가 주어질 때, 사회자가 몇 번째 수를 부른 후 철수가 "빙고"를 외치게 되는지를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄부터 다섯째 줄까지 빙고판에 쓰여진 수가 가장 위 가로줄부터 차례대로 한 줄에 다섯 개씩 빈 칸을 사이에 두고 주어진다. 여섯째 줄부터 열째 줄까지 사회자가 부르는 수가 차례대로 한 줄에 다섯 개씩 빈 칸을 사이에 두고 주어진다. 빙고판에 쓰여진 수와 사회자가 부르는 수는 각각 1부터 25까지의 수가 한 번씩 사용된다.
 *
 * 출력
 * 첫째 줄에 사회자가 몇 번째 수를 부른 후 철수가 "빙고"를 외치게 되는지 출력한다.
 *
 * 예제 입력 1
 * 11 12 2 24 10
 * 16 1 13 3 25
 * 6 20 5 21 17
 * 19 4 8 14 9
 * 22 15 7 23 18
 * 5 10 7 16 2
 * 4 22 8 17 13
 * 3 18 1 6 25
 * 12 19 23 14 21
 * 11 24 9 20 15
 * 예제 출력 1
 * 15
 */

package silver;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class P2578 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    HashMap<Integer, int[]> board;
    List<Integer> answers;
    boolean[][] visited = new boolean[5][5];

    public static void main(String[] args) throws IOException {
        new P2578().solution();
    }

    public void solution() throws IOException {
        board = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board.put(Integer.parseInt(st.nextToken()), new int[]{i, j});
            }
        }

        answers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                answers.add(Integer.parseInt(st.nextToken()));
            }
        }

        int answerCount = 0;
        for (Integer answer : answers) {
            if (answerCount >= 12) {
                if (checkBingo()) {
                    bw.write(answerCount + System.lineSeparator());
                    break;
                }
            }

            int x = board.get(answer)[0];
            int y = board.get(answer)[1];
            visited[x][y] = true;
            answerCount++;
        }
        bw.flush();

        bw.close();
        br.close();
    }

    public boolean checkBingo() {
        int bingoCount = 0;

        for (int i = 0; i < 5; i++) {
            int rowCount = 0;
            int colCount = 0;
            for (int j = 0; j < 5; j++) {
                if (visited[i][j]) {
                    rowCount++;
                }

                if (visited[j][i]) {
                    colCount++;
                }
            }
            bingoCount = rowCount == 5 ? bingoCount + 1 : bingoCount;
            bingoCount = colCount == 5 ? bingoCount + 1 : bingoCount;
        }

        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (visited[i][i]) {
                count++;
            }
        }
        bingoCount = count == 5 ? bingoCount + 1 : bingoCount;

        count = 0;
        for (int i = 0; i < 5; i++) {
            if (visited[i][4 - i]) {
                count++;
            }
        }
        bingoCount = count == 5 ? bingoCount + 1 : bingoCount;

        return bingoCount >= 3 ? true : false;
    }
}
