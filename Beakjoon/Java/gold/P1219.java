/**
 *
 * URL : https://www.acmicpc.net/problem/1219
 *
 * 오민식의 고민
 *
 * 문제
 * 오민식은 세일즈맨이다. 오민식의 회사 사장님은 오민식에게 물건을 최대한 많이 팔아서 최대 이윤을 남기라고 했다.
 *
 * 오민식은 고민에 빠졌다.
 *
 * 어떻게 하면 최대 이윤을 낼 수 있을까?
 *
 * 이 나라에는 N개의 도시가 있다. 도시는 0번부터 N-1번까지 번호 매겨져 있다. 오민식의 여행은 A도시에서 시작해서 B도시에서 끝난다.
 *
 * 오민식이 이용할 수 있는 교통수단은 여러 가지가 있다. 오민식은 모든 교통수단의 출발 도시와 도착 도시를 알고 있고, 비용도 알고 있다. 게다가, 오민식은 각각의 도시를 방문할 때마다 벌 수 있는 돈을 알고있다. 이 값은 도시마다 다르며, 액수는 고정되어있다. 또, 도시를 방문할 때마다 그 돈을 벌게 된다.
 *
 * 오민식은 도착 도시에 도착할 때, 가지고 있는 돈의 액수를 최대로 하려고 한다. 이 최댓값을 구하는 프로그램을 작성하시오.
 *
 * 오민식이 버는 돈보다 쓰는 돈이 많다면, 도착 도시에 도착할 때 가지고 있는 돈의 액수가 음수가 될 수도 있다. 또, 같은 도시를 여러 번 방문할 수 있으며, 그 도시를 방문할 때마다 돈을 벌게 된다. 모든 교통 수단은 입력으로 주어진 방향으로만 이용할 수 있으며, 여러 번 이용할 수도 있다.
 *
 * 입력
 * 첫째 줄에 도시의 수 N과 시작 도시, 도착 도시 그리고 교통 수단의 개수 M이 주어진다. 둘째 줄부터 M개의 줄에는 교통 수단의 정보가 주어진다. 교통 수단의 정보는 “시작 끝 가격”과 같은 형식이다. 마지막 줄에는 오민식이 각 도시에서 벌 수 있는 돈의 최댓값이 0번 도시부터 차례대로 주어진다.
 *
 * N과 M은 50보다 작거나 같고, 돈의 최댓값과 교통 수단의 가격은 1,000,000보다 작거나 같은 음이 아닌 정수이다.
 *
 * 출력
 * 첫째 줄에 도착 도시에 도착할 때, 가지고 있는 돈의 액수의 최댓값을 출력한다. 만약 오민식이 도착 도시에 도착하는 것이 불가능할 때는 "gg"를 출력한다. 그리고, 오민식이 도착 도시에 도착했을 때 돈을 무한히 많이 가지고 있을 수 있다면 "Gee"를 출력한다.
 *
 * 예제 입력 1
 * 5 0 4 7
 * 0 1 13
 * 1 2 17
 * 2 4 20
 * 0 3 22
 * 1 3 4747
 * 2 0 10
 * 3 4 10
 * 0 0 0 0 0
 * 예제 출력 1
 * -32
 * 예제 입력 2
 * 5 0 4 5
 * 0 1 10
 * 1 2 10
 * 2 3 10
 * 3 1 10
 * 2 4 10
 * 0 10 10 110 10
 * 예제 출력 2
 * Gee
 * 예제 입력 3
 * 3 0 2 3
 * 0 1 10
 * 1 0 10
 * 2 1 10
 * 1000 1000 47000
 * 예제 출력 3
 * gg
 * 예제 입력 4
 * 2 0 1 2
 * 0 1 1000
 * 1 1 10
 * 11 11
 * 예제 출력 4
 * Gee
 * 예제 입력 5
 * 1 0 0 1
 * 0 0 10
 * 7
 * 예제 출력 5
 * 7
 * 예제 입력 6
 * 5 0 4 7
 * 0 1 13
 * 1 2 17
 * 2 4 20
 * 0 3 22
 * 1 3 4747
 * 2 0 10
 * 3 4 10
 * 8 10 20 1 100000
 * 예제 출력 6
 * 99988
 */

package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity= Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        EdgeS[] edges = new EdgeS[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            edges[i] = new EdgeS(start, end, price);
        }

        // 도시에서 버는 수입
        long[] cityMoney = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cityMoney[i] = Long.parseLong(st.nextToken());
        }

        // 거리 값 최소값으로 초기화
        long[] distance = new long[N];
        Arrays.fill(distance, Long.MIN_VALUE);

        // 변형한 벨만-포드 알고리즘 수행
        distance[startCity] = cityMoney[startCity];

        // 양수 사이클이 전파되도록 충분히 큰 수로 반복
        for (int i = 0; i <= N + 100; i++) {
            for (int j = 0; j < M; j++) {
                int start = edges[j].start;
                int end = edges[j].end;
                int price = edges[j].price;

                if (distance[start] == Long.MIN_VALUE) {
                    continue;
                } else if (distance[start] == Long.MAX_VALUE) {
                    distance[end] = Long.MAX_VALUE;
                } else if (distance[end] < distance[start] + cityMoney[end] - price) {
                    distance[end] = distance[start] + cityMoney[end] - price;

                    if (i >= N - 1) {
                        distance[end] = Long.MAX_VALUE;
                    }
                }
            }
        }

        if (distance[endCity] == Long.MIN_VALUE) {
            System.out.println("gg");
        } else if (distance[endCity] == Long.MAX_VALUE) {
            System.out.println("Gee");
        } else {
            System.out.println(distance[endCity]);
        }
    }
}

class EdgeS {
    int start;
    int end;
    int price;

    public EdgeS(int start, int end, int price) {
        this.start = start;
        this.end = end;
        this.price = price;
    }
}
