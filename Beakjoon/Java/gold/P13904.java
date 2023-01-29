/**
 *
 * URL : https://www.acmicpc.net/problem/13904
 *
 * 과제
 *
 * 문제
 * 웅찬이는 과제가 많다. 하루에 한 과제를 끝낼 수 있는데, 과제마다 마감일이 있으므로 모든 과제를 끝내지 못할 수도 있다. 과제마다 끝냈을 때 얻을 수 있는 점수가 있는데, 마감일이 지난 과제는 점수를 받을 수 없다.
 *
 * 웅찬이는 가장 점수를 많이 받을 수 있도록 과제를 수행하고 싶다. 웅찬이를 도와 얻을 수 있는 점수의 최댓값을 구하시오.
 *
 * 입력
 * 첫 줄에 정수 N (1 ≤ N ≤ 1,000)이 주어진다.
 *
 * 다음 줄부터 N개의 줄에는 각각 두 정수 d (1 ≤ d ≤ 1,000)와 w (1 ≤ w ≤ 100)가 주어진다. d는 과제 마감일까지 남은 일수를 의미하며, w는 과제의 점수를 의미한다.
 *
 * 출력
 * 얻을 수 있는 점수의 최댓값을 출력한다.
 *
 * 예제 입력 1
 * 7
 * 4 60
 * 4 40
 * 1 20
 * 2 50
 * 3 30
 * 4 10
 * 6 5
 * 예제 출력 1
 * 185
 */

package gold;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class P13904 {
    public static void main(String[] args) throws IOException {
        new P13904().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int maxDay = 0;

        List<Task> list = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            String[] st = br.readLine().split(" ");
            maxDay = Math.max(maxDay, Integer.parseInt(st[0]));
            list.add(new Task(st[0], st[1]));
        }

        list.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o2.d - o1.d;
            }
        });

        Queue<Task> queue = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o2.w - o1.w;
            }
        });

        int total = 0;
        while (maxDay > 0) {
            for (Task task : list) {
                if (maxDay == task.d) {
                    queue.offer(task);
                }
            }

            if (!queue.isEmpty()) {
                total += queue.poll().w;
            }

            maxDay--;
        }

        bw.write(total + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    class Task{
        public int d;
        public int w;

        public Task(String d, String w) {
            this.d = Integer.parseInt(d);
            this.w = Integer.parseInt(w);
        }
    }
}
