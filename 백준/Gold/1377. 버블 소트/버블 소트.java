import java.io.*;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
      int N = Integer.parseInt(br.readLine());
      Node[] A = new Node[N];
      for (int i = 0; i < N; i++) {
        A[i] = new Node(Integer.parseInt(br.readLine()), i);
      }

      Arrays.sort(A);

      int max = 0;
      for (int i = 0; i < N; i++) {
        max = Math.max(max, A[i].idx - i);
      }

      bw.write((max + 1) + "");
      bw.flush();
    }
  }

  static class Node implements Comparable<Node> {
    int value, idx;
    Node (int value, int idx) {
      this.value = value;
      this.idx = idx;
    }

    @Override
    public int compareTo(Node o) {
      if (this.value != o.value) {
        return this.value - o.value;
      } else {
        return this.idx - o.idx;
      }
    }
  }
}
