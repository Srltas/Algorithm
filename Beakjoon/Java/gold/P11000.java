/**
 *
 * URL : https://www.acmicpc.net/problem/11000
 *
 * 강의실 배정
 *
 * 문제
 * 수강신청의 마스터 김종혜 선생님에게 새로운 과제가 주어졌다.
 *
 * 김종혜 선생님한테는 Si에 시작해서 Ti에 끝나는 N개의 수업이 주어지는데, 최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다.
 *
 * 참고로, 수업이 끝난 직후에 다음 수업을 시작할 수 있다. (즉, Ti ≤ Sj 일 경우 i 수업과 j 수업은 같이 들을 수 있다.)
 *
 * 수강신청 대충한 게 찔리면, 선생님을 도와드리자!
 *
 * 입력
 * 첫 번째 줄에 N이 주어진다. (1 ≤ N ≤ 200,000)
 *
 * 이후 N개의 줄에 Si, Ti가 주어진다. (0 ≤ Si < Ti ≤ 109)
 *
 * 출력
 * 강의실의 개수를 출력하라.
 *
 * 예제 입력 1
 * 3
 * 1 3
 * 2 4
 * 3 5
 * 예제 출력 1
 * 2
 */

package gold;

import java.io.*;
import java.util.*;

public class P11000 {
    public static void main(String[] args) throws IOException {
        new P11000().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Class[] classes = new Class[N];
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            classes[n] = new Class(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(classes, new Comparator<Class>() {
            @Override
            public int compare(Class o1, Class o2) {
                if (o1.start == o2.start) {
                    return o1.end - o2.end;
                }
                return o1.start - o2.start;
            }
        });

        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(classes[0].end);

        for (int i = 1; i < N; i++) {
            if (classes[i].start >= queue.peek()) {
                queue.poll();
            }
            queue.offer(classes[i].end);
        }

        bw.write(queue.size() + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    class Class {
        public int start;
        public int end;

        public Class(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
