/**
 *
 * URL : https://www.acmicpc.net/problem/1697
 *
 * 숨바꼭질
 *
 * 문제
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
 *
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 *
 * 출력
 * 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 *
 * 예제 입력 1
 * 5 17
 * 예제 출력 1
 * 4
 */

package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1697 {

    final private int MAX_VALUE = 1000001;
    final private int MIN_VALUE = -1;
    private boolean[] visited = new boolean[MAX_VALUE];

    public static void main(String[] args) throws IOException {
        new P1697().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        bw.write(BFS(N, K) + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    private int BFS(int N, int K) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);

        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size != 0) {
                int n = queue.poll();

                if (n == K) {
                    return depth;
                }

                visited[n] = true;
                if (n - 1 > MIN_VALUE && !visited[n - 1]) {
                    queue.offer(n - 1);
                }

                if (n + 1 < MAX_VALUE && !visited[n + 1]) {
                    queue.offer(n + 1);
                }

                if (n * 2 < MAX_VALUE && !visited[n * 2]) {
                    queue.offer(n * 2);
                }
                size--;
            }
            depth++;
        }
        return -1;
    }
}
