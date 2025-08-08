import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int N = Integer.parseInt(br.readLine());

      int[][] array = new int[N][N];
      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          array[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      System.out.println(pooling(array));
    }
  }

  public static int pooling(int[][] array) {
    int N = array.length;

    if (N == 1) {
      return array[0][0];
    }

    int[][] newArray = new int[N / 2][N / 2];

    for (int i = 0; i < N; i+=2) {
      for (int j = 0; j < N; j+=2) {
        int[] block = new int[4];
        block[0] = array[i][j];
        block[1] = array[i][j + 1];
        block[2] = array[i + 1][j];
        block[3] = array[i + 1][j + 1];

        Arrays.sort(block);

        newArray[i / 2][j / 2] = block[1];
      }
    }

    return pooling(newArray);
  }
}
