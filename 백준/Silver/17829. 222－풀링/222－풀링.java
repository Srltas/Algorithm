import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int N = Integer.parseInt(br.readLine());

      int[][] matrix = new int[N][N];
      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          matrix[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      System.out.println(pooling(matrix));
    }
  }

  public static int pooling(int[][] matrix) {
    int N = matrix.length;

    if (N == 1) {
      return matrix[0][0];
    }

    int[][] newMatrix = new int[N / 2][N / 2];

    for (int i = 0; i < N; i+=2) {
      for (int j = 0; j < N; j+=2) {
        int[] block = new int[4];
        block[0] = matrix[i][j];
        block[1] = matrix[i][j + 1];
        block[2] = matrix[i + 1][j];
        block[3] = matrix[i + 1][j + 1];

        Arrays.sort(block);

        newMatrix[i / 2][j / 2] = block[2];
      }
    }

    return pooling(newMatrix);
  }
}
