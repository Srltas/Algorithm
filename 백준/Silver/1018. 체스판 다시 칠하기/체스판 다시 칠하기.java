import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M;
    static char[][] board;
    static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                check(i, j);
            }
        }

        System.out.println(minCount);
    }

    static void check(int x, int y) {
        int count = 0;

        for (int i = x; i < x + 8; i++) {
            for (int j = y; j < y + 8; j++) {
                char currColor = board[i][j];
                if ((i + j) % 2 == 0) {
                    if (currColor != 'W') count++;
                } else {
                    if (currColor != 'B') count++;
                }
            }
        }

        count = Math.min(count, 64 - count);
        minCount = Math.min(minCount, count);
    }
}
