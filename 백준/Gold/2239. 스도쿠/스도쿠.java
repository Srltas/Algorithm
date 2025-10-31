import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] board = new int[9][9];
    static boolean[][] visited = new boolean[9][9];

    static boolean[][] row = new boolean[9][10];
    static boolean[][] col = new boolean[9][10];
    static boolean[][] box = new boolean[9][10];

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < 9; i++) {
                String line = br.readLine();
                for (int j = 0; j < 9; j++) {
                    int n = line.charAt(j) - '0';
                    board[i][j] = n;
                    if (n > 0) {
                        visited[i][j] = true;
                        row[i][n] = true;
                        col[j][n] = true;
                        box[getBoxIndex(i, j)][n] = true;
                    }
                }
            }

            dfs(0, 0);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
        }
    }

    static boolean dfs(int r, int c) {
        if (c == 9) return dfs(r + 1, 0);
        if (r == 9) return true;
        if (visited[r][c]) return dfs(r, c + 1);
        int b = getBoxIndex(r, c);
        for (int n = 1; n <= 9; n++) {
            if (!row[r][n] && !col[c][n] && !box[b][n]) {
                board[r][c] = n;
                row[r][n] = true;
                col[c][n] = true;
                box[b][n] = true;
                if (dfs(r, c + 1)) return true;
                board[r][c] = 0;
                row[r][n] = false;
                col[c][n] = false;
                box[b][n] = false;
            }
        }
        return false;
    }

    static int getBoxIndex(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }
}
