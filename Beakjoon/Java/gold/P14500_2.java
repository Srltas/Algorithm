/**
 *
 * URL : https://www.acmicpc.net/problem/14500
 *
 * 테트로미노
 *
 * 메모리: 33684KB	시간: 664ms
 *
 * 문제
 * 폴리오미노란 크기가 1×1인 정사각형을 여러 개 이어서 붙인 도형이며, 다음과 같은 조건을 만족해야 한다.
 *
 * 정사각형은 서로 겹치면 안 된다.
 * 도형은 모두 연결되어 있어야 한다.
 * 정사각형의 변끼리 연결되어 있어야 한다. 즉, 꼭짓점과 꼭짓점만 맞닿아 있으면 안 된다.
 * 정사각형 4개를 이어 붙인 폴리오미노는 테트로미노라고 하며, 다음과 같은 5가지가 있다.
 *
 *
 *
 * 아름이는 크기가 N×M인 종이 위에 테트로미노 하나를 놓으려고 한다. 종이는 1×1 크기의 칸으로 나누어져 있으며, 각각의 칸에는 정수가 하나 쓰여 있다.
 *
 * 테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램을 작성하시오.
 *
 * 테트로미노는 반드시 한 정사각형이 정확히 하나의 칸을 포함하도록 놓아야 하며, 회전이나 대칭을 시켜도 된다.
 *
 * 입력
 * 첫째 줄에 종이의 세로 크기 N과 가로 크기 M이 주어진다. (4 ≤ N, M ≤ 500)
 *
 * 둘째 줄부터 N개의 줄에 종이에 쓰여 있는 수가 주어진다. i번째 줄의 j번째 수는 위에서부터 i번째 칸, 왼쪽에서부터 j번째 칸에 쓰여 있는 수이다. 입력으로 주어지는 수는 1,000을 넘지 않는 자연수이다.
 *
 * 출력
 * 첫째 줄에 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값을 출력한다.
 *
 * 예제 입력 1
 * 5 5
 * 1 2 3 4 5
 * 5 4 3 2 1
 * 2 3 4 5 6
 * 6 5 4 3 2
 * 1 2 1 2 1
 * 예제 출력 1
 * 19
 * 예제 입력 2
 * 4 5
 * 1 2 3 4 5
 * 1 2 3 4 5
 * 1 2 3 4 5
 * 1 2 3 4 5
 * 예제 출력 2
 * 20
 * 예제 입력 3
 * 4 10
 * 1 2 1 2 1 2 1 2 1 2
 * 2 1 2 1 2 1 2 1 2 1
 * 1 2 1 2 1 2 1 2 1 2
 * 2 1 2 1 2 1 2 1 2 1
 * 예제 출력 3
 * 7
 */

package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14500_2 {

    static int N;
    static int M;
    static int[][] board;
    static boolean[][] visited;

    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][][] blockTMove = {{{-1, 0}, {1, 0}, {0, 1}}, {{0, -1}, {0, 1}, {1, 0}}, {{0, -1}, {-1 ,0}, {1, 0}}, {{0, -1}, {-1, 0}, {0, 1}}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        long max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                max = Math.max(max, dfs(i, j, 0));
                visited[i][j] =false;
                max = Math.max(max, blockT(i, j));
            }
        }

        System.out.println(max);
    }

    static int blockT(int x, int y) {
        int result = 0;

        for (int[][] moves : blockTMove) {
            int value = board[x][y];
            boolean isFullScan = true;

            for (int[] move : moves) {
                int nextX = x + move[0];
                int nextY = y + move[1];
                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) {
                    value += board[nextX][nextY];
                } else {
                    isFullScan = false;
                    break;
                }
            }
            result = Math.max(result, value);
        }

        return result;
    }

    static int dfs(int x, int y, int depths) {
        int result = 0;
        if (depths == 3) {
            return board[x][y];
        }

        for (int i = 0; i < move.length; i++) {
            int nextX = x + move[i][0];
            int nextY = y + move[i][1];

            if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) {
                if (!visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    result = Math.max(result, dfs(nextX, nextY, depths + 1));
                    visited[nextX][nextY] = false;
                }
            }
        }

        return board[x][y] + result;
    }
}
