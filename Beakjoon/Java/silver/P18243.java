/**
 *
 * URL : https://www.acmicpc.net/problem/18243
 *
 * Small World Network
 *
 * 문제
 * 작은 세상 네트워크(Small World Network)란 Milgram 교수가 1967년에 처음으로 밝혀낸 이론이다.
 *
 * 간단히 설명하자면 전체 네트워크가 거대하더라도 전체가 서로 가깝게 연결될 수 있다는 이론이다.
 *
 * 해당 이론에서 Milgram 교수는 지구에 있는 모든 사람들이 최대 6단계로 연결될 수 있다고 주장하였다.
 *
 * 예를 들어 이 문제를 만든 김 모 씨(23)와 이지은님(27)이 서로 생판 모르는 관계라도 최대 6단계만 거치면 서로 연결이 되어있다는 것이다.
 *
 *
 *
 * 위의 그림에서 정점은 사람, 간선은 친구 관계라 할 때 왼쪽 그래프의 모든 정점들은 서로 최소 6단계 이하로 연결되어 있으므로 작은 세상 네트워크를 만족한다. 그러나 오른쪽 그래프의 초록색 정점끼리는 최소 7단계를 거쳐서 연결되어 있으므로 작은 세상 네트워크를 만족하지 않는다.
 *
 * 이 이론에 대해 의구심이 생긴 김 모 씨는 정말 최대 6단계만 거치면 지구상의 모든 사람들이 서로 연결이 될 수 있는지 확인하고 싶었다.
 *
 * 김 모 씨를 위해 지구상의 모든 사람들의 친구 관계가 주어졌을 때 작은 세상 네트워크가 실제로 만족하는지 확인하는 프로그램을 만들어보자.
 *
 * 입력
 * 첫 번째 줄에 지구에 있는 사람의 수 N과 친구 관계의 개수 K가 주어진다. 모든 사람은 1부터 N까지 번호가 매겨져 있다. (1 ≤ N ≤ 100, 0 ≤ K ≤ N×(N-1)/2)
 *
 * 두 번째 줄부터 K+1번째 줄까지 친구 관계를 나타내는 A B가 한 줄에 하나씩 주어진다. (1 ≤ A, B ≤ N)
 *
 * A와 B가 친구면 B와 A도 친구다. 자기 자신과 친구인 경우는 없다. A와 B의 친구 관계는 중복되어 입력되지 않는다.
 *
 * 출력
 * 해당 네트워크가 작은 세상 네트워크를 만족하면 "Small World!"를, 만족하지 않는다면 "Big World!"를 출력한다.
 *
 * 예제 입력 1
 * 5 5
 * 1 2
 * 2 3
 * 3 5
 * 1 4
 * 1 3
 * 예제 출력 1
 * Small World!
 * 예제 입력 2
 * 10 8
 * 1 2
 * 2 3
 * 3 4
 * 4 5
 * 6 7
 * 7 8
 * 8 9
 * 9 10
 * 예제 출력 2
 * Big World!
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P18243 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    final int MAX_VALUE = 999;

    public static void main(String[] args) throws IOException {
        new P18243().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] distance = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

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

        int result = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }

                result = Math.max(distance[i][j], result);
            }
        }

        bw.write(result <= 6 ? "Small World!" : "Big World!");
        bw.flush();

        bw.close();
        br.close();
    }
}
