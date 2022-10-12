/**
 *
 * URL : https://www.acmicpc.net/problem/11657
 *
 * 타임머신
 *
 * 문제
 * N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 버스가 M개 있다. 각 버스는 A, B, C로 나타낼 수 있는데, A는 시작도시, B는 도착도시, C는 버스를 타고 이동하는데 걸리는 시간이다. 시간 C가 양수가 아닌 경우가 있다. C = 0인 경우는 순간 이동을 하는 경우, C < 0인 경우는 타임머신으로 시간을 되돌아가는 경우이다.
 *
 * 1번 도시에서 출발해서 나머지 도시로 가는 가장 빠른 시간을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 도시의 개수 N (1 ≤ N ≤ 500), 버스 노선의 개수 M (1 ≤ M ≤ 6,000)이 주어진다. 둘째 줄부터 M개의 줄에는 버스 노선의 정보 A, B, C (1 ≤ A, B ≤ N, -10,000 ≤ C ≤ 10,000)가 주어진다.
 *
 * 출력
 * 만약 1번 도시에서 출발해 어떤 도시로 가는 과정에서 시간을 무한히 오래 전으로 되돌릴 수 있다면 첫째 줄에 -1을 출력한다. 그렇지 않다면 N-1개 줄에 걸쳐 각 줄에 1번 도시에서 출발해 2번 도시, 3번 도시, ..., N번 도시로 가는 가장 빠른 시간을 순서대로 출력한다. 만약 해당 도시로 가는 경로가 없다면 대신 -1을 출력한다.
 *
 * 예제 입력 1
 * 3 4
 * 1 2 4
 * 1 3 3
 * 2 3 -1
 * 3 1 -2
 * 예제 출력 1
 * 4
 * 3
 * 예제 입력 2
 * 3 4
 * 1 2 4
 * 1 3 3
 * 2 3 -4
 * 3 1 -2
 * 예제 출력 2
 * -1
 * 예제 입력 3
 * 3 2
 * 1 2 4
 * 1 2 3
 * 예제 출력 3
 * 3
 * -1
 */
package gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P11657 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static long distance[];
    static EdgeT edges[];

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        distance = new long[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        edges = new EdgeT[M + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            edges[i] = new EdgeT(start, end, time);
        }

        // 벨만-포드 알고리즘 수행
        distance[1] = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                EdgeT edgeT = edges[j];

                if (distance[edgeT.start] != Integer.MAX_VALUE
                        && distance[edgeT.end] > distance[edgeT.start] + edgeT.time) {
                    distance[edgeT.end] = distance[edgeT.start] + edgeT.time;
                }
            }
        }

        boolean mCycle = false;
        for (int i = 0; i < M; i++) {
            EdgeT edgeT = edges[i];
            if (distance[edgeT.start] != Integer.MAX_VALUE
                    && distance[edgeT.end] > distance[edgeT.start] + edgeT.time)  {
                mCycle = true;
            }
        }

        if (!mCycle) {
            for (int i = 2; i <= N; i++) {
                if (distance[i] == Integer.MAX_VALUE) {
                    System.out.println("-1");
                } else {
                    System.out.println(distance[i]);
                }
            }
        } else {
            System.out.println("-1");
        }
    }
}

class EdgeT {
    int start, end, time;

    public EdgeT(int start, int end, int time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }
}
