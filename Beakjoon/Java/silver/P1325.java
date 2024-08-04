/**
 *
 * URL : https://www.acmicpc.net/problem/1325
 *
 * 효율적 해킹
 *
 * 문제
 * 해커 김지민은 잘 알려진 어느 회사를 해킹하려고 한다. 이 회사는 N개의 컴퓨터로 이루어져 있다. 김지민은 귀찮기 때문에, 한 번의 해킹으로 여러 개의 컴퓨터를 해킹 할 수 있는 컴퓨터를 해킹하려고 한다.
 *
 * 이 회사의 컴퓨터는 신뢰하는 관계와, 신뢰하지 않는 관계로 이루어져 있는데, A가 B를 신뢰하는 경우에는 B를 해킹하면, A도 해킹할 수 있다는 소리다.
 *
 * 이 회사의 컴퓨터의 신뢰하는 관계가 주어졌을 때, 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에, N과 M이 들어온다. N은 10,000보다 작거나 같은 자연수, M은 100,000보다 작거나 같은 자연수이다. 둘째 줄부터 M개의 줄에 신뢰하는 관계가 A B와 같은 형식으로 들어오며, "A가 B를 신뢰한다"를 의미한다. 컴퓨터는 1번부터 N번까지 번호가 하나씩 매겨져 있다.
 *
 * 출력
 * 첫째 줄에, 김지민이 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 오름차순으로 출력한다.
 *
 * 예제 입력 1  복사
 * 5 4
 * 3 1
 * 3 2
 * 4 3
 * 5 3
 * 예제 출력 1  복사
 * 1 2
 */

package silver;

import java.io.*;
import java.util.*;

public class P1325 {
    static List<Integer>[] computers;
    static boolean[] visited;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            computers = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                computers[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                computers[S].add(E);
            }

            answer = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                visited = new boolean[N + 1];
                BFS(i);
            }

            int maxVal = 0;
            for (int i = 1; i <= N; i++) {
                maxVal = Math.max(maxVal, answer[i]);
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= N; i++) {
                if (answer[i] == maxVal) {
                    sb.append(i + " ");
                }
            }
            bw.write(sb.toString());
            bw.flush();
        }
    }

    static void BFS(int node) {
        Queue<Integer> q = new LinkedList<>();

        visited[node] = true;
        q.add(node);
        while (!q.isEmpty()) {
            int n = q.poll();
            for (int i : computers[n]) {
                if (!visited[i]) {
                    visited[i] = true;
                    answer[i]++;
                    q.add(i);
                }
            }
        }
    }
}
