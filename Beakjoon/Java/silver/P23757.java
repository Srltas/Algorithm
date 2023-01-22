/**
 *
 * URL : https://www.acmicpc.net/problem/23757
 *
 * 아이들과 선물 상자
 *
 * 문제
 * 상훈이는 $N$개의 선물 상자를 가지고 있다. 선물 상자에는 현재 담겨있는 선물의 개수가 적혀있다.
 *
 * 선물을 받을 아이들이 $M$명 있다. 아이들은 각자 $1$에서 $M$까지의 서로 다른 번호를 하나씩 부여받았다.
 *
 *  $1$번 아이부터 $M$번 아이까지 한 번에 한 명씩, 현재 선물이 가장 많이 담겨있는 상자에서 각자 원하는 만큼 선물을 가져간다. 이 때, 앞서 누군가 선물을 가져갔던 선물 상자에서 또다시 가져가도 상관없다.
 *
 * 하지만 상자에 자신이 원하는 것보다 적은 개수의 선물이 들어있다면, 선물을 가져가지 못해 실망한다.
 *
 * 상훈이는 한 명이라도 실망하지 않고 모두가 선물을 가져갈 수 있는지 궁금하다.
 *
 * 입력
 * 첫째 줄에 선물 상자의 수 $N$ 과 아이들의 수 $M$이 공백을 사이에 두고 주어진다. ($1\le M \le N\le 10^5$)
 *
 * 둘째 줄에 각 선물 상자에 들어있는 선물의 개수 $c_1,c_2,\ldots ,c_N$이 공백을 사이에 두고 주어진다. ($1\le c_i\le 10^5$)
 *
 * 셋째 줄에 아이들의 번호 순으로 각 아이가 원하는 선물의 개수 $w_1,w_2,\ldots ,w_M$이 공백을 사이에 두고 주어진다. ($1\le w_i\le 10^5$)
 *
 * 출력
 * 모든 아이들이 실망하지 않고 각자 원하는 만큼 선물을 가져갈 수 있으면 $1$을, 그렇지 않으면 $0$을 출력한다.
 *
 * 예제 입력 1
 * 4 4
 * 4 3 2 1
 * 3 1 2 1
 * 예제 출력 1
 * 1
 * 예제 입력 2
 * 4 3
 * 4 3 2 1
 * 3 1 5
 * 예제 출력 2
 * 0
 */

package silver;

import java.io.*;
import java.util.*;

public class P23757 {
    public static void main(String[] args) throws IOException {
        new P23757().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> presents = new PriorityQueue<>(Comparator.reverseOrder());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            presents.offer(Long.parseLong(st.nextToken()));
        }

        List<Long> children = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            children.add(Long.parseLong(st.nextToken()));
        }

        boolean isPossible = true;
        for (long child : children) {
            long present = presents.poll();

            if (present - child >= 0) {
                presents.offer(present - child);
            } else {
                isPossible = false;
                break;
            }
        }

        bw.write((isPossible ? "1" : "0") + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }
}
