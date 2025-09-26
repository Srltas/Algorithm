import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int SIZE = 9;
    static int[][] board;
    static List<int[]> blanks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            board = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < SIZE; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num == 0) blanks.add(new int[]{i, j});
                    board[i][j] = num;
                }
            }

            solve(0);

            StringBuilder out = new StringBuilder();
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    out.append(board[i][j]).append(j == SIZE - 1 ? '\n' : ' ');
                }
            }
            System.out.println(out);
        }
    }

    static boolean solve(int index) {
        if (index == blanks.size()) {
            return true;
        }
        int[] point = blanks.get(index);
        int r = point[0], c = point[1];
        for (int i = 1; i <= SIZE; i++) {
            if (isValid(point[0], point[1], i)) {
                board[r][c] = i;
                if (solve(index + 1)) return true;
                board[r][c] = 0;
            }
        }
        return false;
    }

    static boolean isValid(int x, int y, int n) {
        for (int i = 0; i < SIZE; i++) {
            if (board[x][i] == n || board[i][y] == n) return false;
        }

        int nx = (x / 3) * 3;
        int ny = (y / 3) * 3;
        for (int i = nx; i < nx + 3; i++) {
            for (int j = ny; j < ny + 3; j++) {
                if (board[i][j] == n) return false;
            }
        }

        return true;
    }
}
