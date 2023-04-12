/**
 *
 * URL : https://www.acmicpc.net/problem/2660
 *
 * 회장뽑기
 *
 * 문제
 * 월드컵 축구의 응원을 위한 모임에서 회장을 선출하려고 한다. 이 모임은 만들어진지 얼마 되지 않았기 때문에 회원 사이에 서로 모르는 사람도 있지만, 몇 사람을 통하면 모두가 서로 알 수 있다. 각 회원은 다른 회원들과 가까운 정도에 따라 점수를 받게 된다.
 *
 * 예를 들어 어느 회원이 다른 모든 회원과 친구이면, 이 회원의 점수는 1점이다. 어느 회원의 점수가 2점이면, 다른 모든 회원이 친구이거나 친구의 친구임을 말한다. 또한 어느 회원의 점수가 3점이면, 다른 모든 회원이 친구이거나, 친구의 친구이거나, 친구의 친구의 친구임을 말한다.
 *
 * 4점, 5점 등은 같은 방법으로 정해진다. 각 회원의 점수를 정할 때 주의할 점은 어떤 두 회원이 친구사이이면서 동시에 친구의 친구사이이면, 이 두사람은 친구사이라고 본다.
 *
 * 회장은 회원들 중에서 점수가 가장 작은 사람이 된다. 회장의 점수와 회장이 될 수 있는 모든 사람을 찾는 프로그램을 작성하시오.
 *
 * 입력
 * 입력의 첫째 줄에는 회원의 수가 있다. 단, 회원의 수는 50명을 넘지 않는다. 둘째 줄 이후로는 한 줄에 두 개의 회원번호가 있는데, 이것은 두 회원이 서로 친구임을 나타낸다. 회원번호는 1부터 회원의 수만큼 붙어 있다. 마지막 줄에는 -1이 두 개 들어있다.
 *
 * 출력
 * 첫째 줄에는 회장 후보의 점수와 후보의 수를 출력하고, 두 번째 줄에는 회장 후보를 오름차순으로 모두 출력한다.
 *
 * 예제 입력 1
 * 5
 * 1 2
 * 2 3
 * 3 4
 * 4 5
 * 2 4
 * 5 3
 * -1 -1
 * 예제 출력 1
 * 2 3
 * 2 3 4
 */

package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P2660 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new P2660().solution();
    }

    public void solution() throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][]distance = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = 50;
                }
            }
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            if (S == -1 && E == -1) {
                break;
            }

            distance[S][E] = 1;
            distance[E][S] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        int[] maxValue = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                maxValue[i] = Math.max(maxValue[i], distance[i][j]);
            }
        }

        int totalMinValue = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            totalMinValue = Math.min(totalMinValue, maxValue[i]);
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (totalMinValue == maxValue[i]) {
                list.add(i);
            }
        }

        bw.write(totalMinValue + " " + list.size() + System.lineSeparator());
        Collections.sort(list);
        for (Integer integer : list) {
            bw.write(integer + " ");
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
