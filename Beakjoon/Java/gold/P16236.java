/**
 * 문제
 * N×N 크기의 공간에 물고기 M마리와 아기 상어 1마리가 있다. 공간은 1×1 크기의 정사각형 칸으로 나누어져 있다. 한 칸에는 물고기가 최대 1마리 존재한다.
 *
 * 아기 상어와 물고기는 모두 크기를 가지고 있고, 이 크기는 자연수이다. 가장 처음에 아기 상어의 크기는 2이고, 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동한다.
 *
 * 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다. 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다. 따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.
 *
 * 아기 상어가 어디로 이동할지 결정하는 방법은 아래와 같다.
 *
 * 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
 * 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
 * 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
 * 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
 * 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
 * 아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정한다. 즉, 아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면, 이동과 동시에 물고기를 먹는다. 물고기를 먹으면, 그 칸은 빈 칸이 된다.
 *
 * 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.
 *
 * 공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 공간의 크기 N(2 ≤ N ≤ 20)이 주어진다.
 *
 * 둘째 줄부터 N개의 줄에 공간의 상태가 주어진다. 공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9로 이루어져 있고, 아래와 같은 의미를 가진다.
 *
 * 0: 빈 칸
 * 1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
 * 9: 아기 상어의 위치
 * 아기 상어는 공간에 한 마리 있다.
 *
 * 출력
 * 첫째 줄에 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간을 출력한다.
 *
 * 예제 입력 1
 * 3
 * 0 0 0
 * 0 0 0
 * 0 9 0
 * 예제 출력 1
 * 0
 * 예제 입력 2
 * 3
 * 0 0 1
 * 0 0 0
 * 0 9 0
 * 예제 출력 2
 * 3
 * 예제 입력 3
 * 4
 * 4 3 2 1
 * 0 0 0 0
 * 0 0 9 0
 * 1 2 3 4
 * 예제 출력 3
 * 14
 * 예제 입력 4
 * 6
 * 5 4 3 2 3 4
 * 4 3 2 3 4 5
 * 3 2 9 5 6 6
 * 2 1 2 3 4 5
 * 3 2 1 6 5 4
 * 6 6 6 6 6 6
 * 예제 출력 4
 * 60
 * 예제 입력 5
 * 6
 * 6 0 6 0 6 1
 * 0 0 0 0 0 2
 * 2 3 4 5 6 6
 * 0 0 0 0 0 2
 * 0 2 0 0 0 0
 * 3 9 3 0 0 1
 * 예제 출력 5
 * 48
 * 예제 입력 6
 * 6
 * 1 1 1 1 1 1
 * 2 2 6 2 2 3
 * 2 2 5 2 2 3
 * 2 2 2 4 6 3
 * 0 0 0 0 0 6
 * 0 0 0 0 0 9
 * 예제 출력 6
 * 39
 */

package gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P16236 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] dx = {-1, 0, 0, 1};
    int[] dy = {0, -1, 1, 0};

    int N;
    int[][] array;
    boolean[][] visited;
    Shark shark;

    public static void main(String[] args) throws IOException {
        new P16236().solution();
    }

    public void solution() throws IOException {
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
                if (array[i][j] == 9) {
                    shark = new Shark(new Node(i, j));
                    array[i][j] = 0;
                }
            }
        }

        int totalTime = 0;
        while (true) {
            int time = BFS(shark.position);

            if (time == -1) {
                break;
            }
            totalTime += time;
        }

        bw.write(totalTime + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    public int BFS(Node startPosition) {
        Queue<Node> queue = new LinkedList<>();
        visited = new boolean[N][N];
        boolean isEatFish = false;
        Node nextEatFishPosition = new Node(N, N);
        int time = 0;

        queue.offer(startPosition);
        visited[startPosition.x][startPosition.y] = true;

        while (!queue.isEmpty()) {
            if (isEatFish) {
                 break;
            }

            int queueSize = queue.size();
            time++;

            for (int i = 0; i < queueSize; i++) {
                Node nextPosition = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int x = nextPosition.x + dx[j];
                    int y = nextPosition.y + dy[j];

                    if (x >= 0 && y >= 0 && x < N && y < N && !visited[x][y]) {
                        if (array[x][y] == 0 || shark.size == array[x][y]) {
                            queue.offer(new Node(x, y));
                            visited[x][y] = true;
                        } else if (array[x][y] != 0 && shark.size > array[x][y]) {
                            isEatFish = true;
                            nextEatFishPosition = findNextNode(nextEatFishPosition, x, y);
                        }
                    }
                }
            }
        }

        if (isEatFish) {
            shark.position = nextEatFishPosition;
            array[nextEatFishPosition.x][nextEatFishPosition.y] = 0;
            shark.eat();
            return time;
        } else {
            return -1;
        }
    }

    public Node findNextNode(Node position, int x, int y) {
        if (x < position.x) {
            return new Node(x, y);
        } else if (x == position.x && y < position.y) {
            return new Node(x, y);
        }
        return position;
    }

    static class Shark {
        int eatCount;
        int size;
        Node position;

        public Shark(Node position) {
            this.position = position;
            size = 2;
        }

        public void eat() {
            eatCount++;
            if (eatCount == size) {
                eatCount = 0;
                size++;
            }
        }
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
