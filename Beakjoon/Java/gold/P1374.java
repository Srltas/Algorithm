/**
 *
 * URL : https://www.acmicpc.net/problem/1374
 *
 * 강의실
 *
 * 문제
 * N개의 강의가 있다. 우리는 모든 강의의 시작하는 시간과 끝나는 시간을 알고 있다. 이때, 우리는 최대한 적은 수의 강의실을 사용하여 모든 강의가 이루어지게 하고 싶다.
 *
 * 물론, 한 강의실에서는 동시에 2개 이상의 강의를 진행할 수 없고, 한 강의의 종료시간과 다른 강의의 시작시간이 겹치는 것은 상관없다. 필요한 최소 강의실의 수를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 강의의 개수 N(1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N개의 줄에 걸쳐 각 줄마다 세 개의 정수가 주어지는데, 순서대로 강의 번호, 강의 시작 시간, 강의 종료 시간을 의미한다. 강의 번호는 1부터 N까지 붙어 있으며, 입력에서 꼭 순서대로 주어지지 않을 수 있으나 한 번씩만 주어진다. 강의 시작 시간과 강의 종료 시간은 0 이상 10억 이하의 정수이고, 시작 시간은 종료 시간보다 작다.
 *
 * 출력
 * 첫째 줄에 필요한 최소 강의실 개수를 출력한다.
 *
 * 예제 입력 1
 * 8
 * 6 15 21
 * 7 20 25
 * 1 3 8
 * 3 2 14
 * 8 6 27
 * 2 7 13
 * 4 12 18
 * 5 6 20
 * 예제 출력 1
 * 5
 */

package gold;

import java.io.*;
import java.util.*;

public class P1374 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new P1374().solution();
    }

    public void solution() throws IOException {
        int N = Integer.parseInt(br.readLine());

        List<Node> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list.add(new Node(n, s, e));
        }

        Collections.sort(list);

        int max = Integer.MIN_VALUE;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(0);
        for (int i = 0; i < N; i++) {
            while (!queue.isEmpty() && queue.peek() <= list.get(i).s) {
                queue.poll();
            }

            queue.add(list.get(i).e);
            max = Math.max(max, queue.size());
        }
        bw.write(max + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    static class Node implements Comparable<Node> {
        int n;
        int s;
        int e;

        public Node(int n, int s, int e) {
            this.n = n;
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Node o) {
            return this.s - o.s;
        }

    }
}
