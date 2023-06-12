/**
 *
 * URL : https://www.acmicpc.net/problem/5464
 *
 * 주차장
 *
 * 문제
 * 시내 주차장은 1부터 N까지 번호가 매겨진 N개의 주차 공간을 가지고 있다. 이 주차장은 매일 아침 모든 주차 공간이 비어 있는 상태에서 영업을 시작하며, 하룻동안 다음과 같은 방식으로 운영된다. 차가 주차장에 도착하면, 주차장 관리인이 비어있는 주차 공간이 있는지를 검사한다. 만일 비어있는 공간이 없으면, 차량을 빈 공간이 생길 때까지 입구에서 기다리게 한다. 만일 빈 주차 공간이 하나만 있거나 또는 빈 주차 공간이 없다가 한 대의 차량이 주차장을 떠나면 곧바로 그 장소에 주차를 하게 한다. 만일 빈 주차 공간이 여러 곳이 있으면, 그 중 번호가 가장 작은 주차 공간에 주차하도록 한다. 만일 주차장에 여러 대의 차량이 도착하면, 일단 도착한 순서대로 입구의 대기장소에서 줄을 서서 기다려야 한다. 대기장소는 큐(queue)와 같이, 먼저 대기한 차량부터 주차한다.
 *
 * 주차료는 주차한 시간이 아닌 차량의 무게에 비례하는 방식으로 책정된다. 주차료는 차랑의 무게에다 주차 공간마다 따로 책정된 단위 무게당 요금을 곱한 가격이다.
 *
 * 주차장 관리원은 오늘 M대의 차량이 주차장을 이용할 것이라는 것을 알고 있다. 또한, 차량들이 들어오고 나가는 순서도 알고 있다.
 *
 * 주차 공간별 요금과 차량들의 무게와 출입 순서가 주어질 때, 오늘 하룻동안 주차장이 벌어들일 총 수입을 계산하는 프로그램을 작성하라.
 *
 * 입력
 * 반드시 표준 입력으로부터 다음의 데이터를 읽어야 한다.
 *
 * 첫 번째 줄에는 정수 N과 M이 빈칸을 사이에 두고 주어진다.
 * 그 다음 N개의 줄에는 주차 공간들의 단위 무게당 요금을 나타내는 정수들이 주어진다. 그 중 s번째 줄에는 주차 공간 s의 단위 무게당 요금 Rs가 들어있다.
 * 그 다음 M개의 줄에는 차량들의 무게를 나타내는 정수들이 주어진다. 차량들은 1 부터 M 까지 번호로 구분되고, 이 번호는 출입 순서와는 상관없다. 이 M개의 줄 중 k번째 줄에는 차량 k의 무게를 나타내는 정수 Wk가 들어있다.
 * 그 다음 2*M 개의 줄에는 차량들의 주차장 출입 순서를 나타내는 정수들이 한 줄에 하나씩 주어진다. 양의 정수 i는 차량 i가 주차장에 들어오는 것을 의미하고, 음의 정수 -i는 차량 i가 주차장에서 나가는 것을 의미한다. 주차장에 들어오지 않은 차량이 주차장에서 나가는 경우는 없다. 1 번부터 M 번까지 모든 차량은 정확하게 한 번씩 주차장에 들어오고, 한 번씩 주차장에서 나간다. 또한 입구에서 대기 중인 차량이 주차를 하지 못하고 나가는 경우는 없다.
 * 1 ≤ N ≤ 100 주차 공간의 수
 * 1 ≤ M ≤ 2,000 차량의 수
 * 1 ≤ Rs ≤ 100 주차 공간 s의 단위 무게당 요금
 * 1 ≤ Wk ≤ 10,000 차량 k의 무게
 * 출력
 * 출력은 반드시 표준 출력으로 하여야 하며, 하나의 줄에 한 개의 정수를 출력한다. 이 정수는 오늘 하룻동안 주차장이 벌어들인 총 수입이다.
 *
 * 예제 입력 1
 * 3 4
 * 2
 * 3
 * 5
 * 200
 * 100
 * 300
 * 800
 * 3
 * 2
 * -3
 * 1
 * 4
 * -4
 * -2
 * -1
 * 예제 출력 1
 * 5300
 * 예제 입력 2
 * 2 4
 * 5
 * 2
 * 100
 * 500
 * 1000
 * 2000
 * 3
 * 1
 * 2
 * 4
 * -1
 * -3
 * -2
 * -4
 * 예제 출력 2
 * 16200
 */

package silver;

import java.io.*;
import java.util.*;

public class P5464 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  PriorityQueue<ParkingSpace> parkingSpaceQueue = new PriorityQueue();
  Queue<Integer> input = new LinkedList<>();
  int[] cars;
  ParkingSpace[] parking;

  public static void main(String[] args) throws IOException {
      new P5464().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    parkingSpaceQueue = new PriorityQueue();
    input = new LinkedList<>();
    cars = new int[M + 1];
    parking = new ParkingSpace[M + 1];

    // 주차장 Queue
    for (int i = 1; i <= N; i++) {
      parkingSpaceQueue.offer(new ParkingSpace(i, Integer.parseInt(br.readLine())));
    }

    // 자동차 무게
    for (int i = 1; i <= M; i++) {
      cars[i] = Integer.parseInt(br.readLine());
    }

    int totalMoney = 0;
    for (int i = 0; i < M * 2; i++) {
      int car = Integer.parseInt(br.readLine());
      // 들어옴
      if (car > 0) {
        // 대기 중인 차가 있다면 먼저 넣어준다.
        if (!input.isEmpty() && !parkingSpaceQueue.isEmpty()) {
          totalMoney += parkingCar(input.poll());
          input.offer(car);
          continue;
        }

        // 대기 중인 차가 없지만, 주차 공간이 없는 경우
        if (parkingSpaceQueue.isEmpty()) {
          input.offer(car);
          continue;
        }

        // 대기 중인 차가 없고, 주차 공간이 있는 경우
        totalMoney += parkingCar(car);
      } else {
        // 나감
        parkingSpaceQueue.offer(parking[Math.abs(car)]);

        // 대기하고 있는 차가 있으면 넣어줌
        if (!input.isEmpty()) {
          totalMoney += parkingCar(input.poll());
        }
      }
    }

    bw.write(totalMoney + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private int parkingCar(int car) {
    ParkingSpace parkingSpace = parkingSpaceQueue.poll();
    parking[car] = parkingSpace;
    return parkingSpace.money * cars[car];
  }

  static class ParkingSpace implements Comparable<ParkingSpace> {
    int no;
    int money;

    public ParkingSpace(int no, int money) {
      this.no = no;
      this.money = money;
    }

    @Override
    public int compareTo(ParkingSpace o) {
      return this.no - o.no;
    }
  }
}
