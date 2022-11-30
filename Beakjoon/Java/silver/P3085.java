/**
 *
 * URL : https://www.acmicpc.net/problem/3085
 *
 * 사탕 게임
 *
 * 문제
 * 상근이는 어렸을 적에 "봄보니 (Bomboni)" 게임을 즐겨했다.
 *
 * 가장 처음에 N×N크기에 사탕을 채워 놓는다. 사탕의 색은 모두 같지 않을 수도 있다. 상근이는 사탕의 색이 다른 인접한 두 칸을 고른다. 그 다음 고른 칸에 들어있는 사탕을 서로 교환한다. 이제, 모두 같은 색으로 이루어져 있는 가장 긴 연속 부분(행 또는 열)을 고른 다음 그 사탕을 모두 먹는다.
 *
 * 사탕이 채워진 상태가 주어졌을 때, 상근이가 먹을 수 있는 사탕의 최대 개수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 보드의 크기 N이 주어진다. (3 ≤ N ≤ 50)
 *
 * 다음 N개 줄에는 보드에 채워져 있는 사탕의 색상이 주어진다. 빨간색은 C, 파란색은 P, 초록색은 Z, 노란색은 Y로 주어진다.
 *
 * 사탕의 색이 다른 인접한 두 칸이 존재하는 입력만 주어진다.
 *
 * 출력
 * 첫째 줄에 상근이가 먹을 수 있는 사탕의 최대 개수를 출력한다.
 *
 * 예제 입력 1
 * 3
 * CCP
 * CCP
 * PPC
 * 예제 출력 1
 * 3
 * 예제 입력 2
 * 4
 * PPPP
 * CYZY
 * CCPY
 * PPCC
 * 예제 출력 2
 * 4
 * 예제 입력 3
 * 5
 * YCPZY
 * CYZZP
 * CCPPP
 * YCYZC
 * CPPZZ
 * 예제 출력 3
 * 4
 */

package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P3085 {

    static int N;
    static char[][] board;
    static int max;
    static char[] candies = {'C', 'P', 'Z', 'Y'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = temp.charAt(j);
            }
        }

        // 초기에 최댓값 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < candies.length; j++) {
                countRow(candies[j], i);
                countColumn(candies[j], i);
            }
        }

        // 사탕 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (i + 1 < N) {
                    changeCandy(i, j, i + 1, j);
                    countRow(board[i][j], i);
                    countColumn(board[i][j], j);
                    countRow(board[i + 1][j], i + 1);
                    countColumn(board[i + 1][j], j);
                    changeCandy(i, j, i + 1, j);
                }

                if (j + 1 < N) {
                    changeCandy(i, j, i, j + 1);
                    countRow(board[i][j], i);
                    countColumn(board[i][j], j);
                    countRow(board[i][j + 1], i);
                    countColumn(board[i][j + 1], j + 1);
                    changeCandy(i, j, i, j + 1);
                }

            }
        }

        System.out.println(max);
    }

    // 자리 바꾸기
    static void changeCandy(int x1, int y1, int x2, int y2) {
        char temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2]= temp;
    }


    // 가로줄 찾기
    static void countRow(char candy, int n)  {
        int count = 0;

        for (int i = 0; i < N; i++) {
            if (candy == board[n][i]) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0;
            }
        }

        max = Math.max(max, count);
    }

    // 세로줄 찾기
    static void countColumn(char candy, int n) {
        int count = 0;

        for (int i = 0; i < N; i++) {
            if (candy == board[i][n]) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0;
            }
        }

        max = Math.max(max, count);
    }
}
