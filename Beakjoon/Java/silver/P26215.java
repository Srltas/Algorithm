/**
 *
 * URL : https://www.acmicpc.net/problem/26215
 *
 * 눈 치우기
 *
 * 문제
 * 지난 밤 겨울 숲에는 눈이 많이 내렸다. 당신은 숲의 주민들을 위해 눈이 오지 않는 동안 모든 집 앞의 눈을 치우고자 한다.
 *
 * 당신은 1분에 한 번씩 두 집을 선택해서 두 집 앞의 눈을 각각 1만큼 치우거나, 한 집을 선택해서 그 집 앞의 눈을 1만큼 치울 수 있다.
 *
 * 모든 집 앞의 눈을 전부 치울 때까지 걸리는 최소 시간은 얼마일까?
 *
 * 입력
 * 첫 줄에 집의 수를 의미하는 정수
 * N (1 <= N <= 100)이 주어진다.
 *
 * 다음 줄에는 각각의 집 앞에 쌓여 있는 눈의 양을 나타내는 정수
 * $ai (1 <= ai <= 2000)이 주어진다.
 *
 * 출력
 * 모든 집 앞의 눈을 치우는 데 최소 몇 분이 걸리는지를 출력한다. 24시간(1440분)이 넘게 걸릴 경우 -1을 출력한다.
 *
 * 예제 입력 1
 * 3
 * 1 2 3
 * 예제 출력 1
 * 3
 * 집 2와 집 3 앞의 눈을 치우고, 집 2와 집 3 앞의 눈을 치우고, 이후에 집 1과 집 3 앞의 눈을 치우면 3분만에 모든 집 앞의 눈을 치울 수 있다.
 *
 * 예제 입력 2
 * 3
 * 1 2 5
 * 예제 출력 2
 * 5
 * 집 2와 집 3 앞의 눈을 치우고, 집 2와 집 3 앞의 눈을 치우고, 집 1과 집 3 앞의 눈을 치운 뒤 집 3 앞의 눈을 두 번 치우면 5분만에 모든 집 앞의 눈을 치울 수 있다.
 *
 * 예제 입력 3
 * 1
 * 1441
 * 예제 출력 3
 * -1
 * 눈을 치우는 데 1441분이 걸리므로 24시간 안에 눈을 전부 치울 수 없다.
 *
 * 예제 입력 4
 * 1
 * 1440
 * 예제 출력 4
 * 1440
 */

package silver;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P26215 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new P26215().solution();
    }

    public void solution() throws IOException {
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < N; i++) {
            queue.offer(Integer.parseInt(st.nextToken()));
        }

        int time = 0;
        boolean isOver = false;
        while (!queue.isEmpty()) {
            if (queue.size() > 1) {
                int homeA = queue.poll() - 1;
                int homeB = queue.poll() - 1;
                if (homeA > 0) {
                    queue.offer(homeA);
                }
                if (homeB > 0) {
                    queue.offer(homeB);
                }
            } else {
                int home = queue.poll() - 1;
                if (home > 0) {
                    queue.offer(home);
                }
            }
            time++;
            if (time > 1440) {
                isOver = true;
                break;
            }
        }
        bw.write((isOver ? "-1" : time) + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }
}
