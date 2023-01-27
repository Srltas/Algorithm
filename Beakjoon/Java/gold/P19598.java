/**
 *
 * URL : https://www.acmicpc.net/problem/19598
 *
 * 최소 회의실 개수
 *
 * 문제
 * 서준이는 아빠로부터 N개의 회의를 모두 진행할 수 있는 최소 회의실 개수를 구하라는 미션을 받았다. 각 회의는 시작 시간과 끝나는 시간이 주어지고 한 회의실에서 동시에 두 개 이상의 회의가 진행될 수 없다. 단, 회의는 한번 시작되면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다. 회의의 시작 시간은 끝나는 시간보다 항상 작다. N이 너무 커서 괴로워 하는 우리 서준이를 도와주자.
 *
 * 입력
 * 첫째 줄에 배열의 크기 N(1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N+1 줄까지 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다. 시작 시간과 끝나는 시간은 231−1보다 작거나 같은 자연수 또는 0이다.
 *
 * 출력
 * 첫째 줄에 최소 회의실 개수를 출력한다.
 *
 * 예제 입력 1
 * 3
 * 0 40
 * 15 30
 * 5 10
 * 예제 출력 1
 * 2
 * 2개 회의실로 3개 회의를 모두 진행할 수 있다. 예를 들어, 첫번째 회의실에서 첫번째 회의를 진행하고 두번째 회의실에서 두번째 회의와 세번째 회의를 진행하면 된다. 1개 회의실로 3개 회의를 진행할 수 없고 3개 이상의 회의실로 3개 회의를 모두 진행할 수 있지만 최소 회의실 개수를 구해야 하기 때문에 2가 정답이 된다.
 *
 * 예제 입력 2
 * 2
 * 10 20
 * 5 10
 * 예제 출력 2
 */

package gold;

import java.io.*;
import java.util.*;

public class P19598 {
    public static void main(String[] args) throws IOException {
        new P19598().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int N = Integer.parseInt(br.readLine());
        Meeting[] meetings = new Meeting[N];
        for (int i = 0; i < N; i++) {
            String[] st = br.readLine().split(" ");
            meetings[i] = new Meeting(Integer.parseInt(st[0]), Integer.parseInt(st[1]));
        }

        Arrays.sort(meetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                if (o1.start == o2.start) {
                    return o1.end - o2.end;
                }
                return o1.start - o2.start;
            }
        });

        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(meetings[0].end);

        for (int i = 1; i < N; i++) {
            if (meetings[i].start >= queue.peek()) {
                queue.poll();
            }
            queue.offer(meetings[i].end);
        }

        bw.write(queue.size() + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    class Meeting {
        public int start;
        public int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
