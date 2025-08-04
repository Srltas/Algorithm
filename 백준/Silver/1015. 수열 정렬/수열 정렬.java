import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int N = Integer.parseInt(br.readLine());

      int[] A = new int[N];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
        A[i] = Integer.parseInt(st.nextToken());
      }

      List<Pair> indexedList = new ArrayList<>();
      for (int i = 0; i < N; i++) {
        indexedList.add(new Pair(A[i], i));
      }

      indexedList.sort(Comparator.comparingInt((Pair p) -> p.value).thenComparingInt(p -> p.index));

      int[] P = new int[N];
      for (int newIndex = 0; newIndex < N; newIndex++) {
        Pair p = indexedList.get(newIndex);
        P[p.index] = newIndex;
      }

      StringBuilder sb = new StringBuilder();
      for (int val : P) {
        sb.append(val).append(' ');
      }
      System.out.println(sb);
    }
  }

  static class Pair {
    int value;
    int index;

    Pair(int value, int index) {
      this.value = value;
      this.index = index;
    }
  }
}
