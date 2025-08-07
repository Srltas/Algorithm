import java.io.*;
import java.util.PriorityQueue;

public class Main {
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
      int N = Integer.parseInt(br.readLine());

      PriorityQueue<Integer> queue = new PriorityQueue<>();
      for (int i = 0; i < N; i++) {
        queue.add(Integer.parseInt(br.readLine()));
      }

      int result = 0;
      while (queue.size() > 1) {
        int num1 = queue.poll();
        int num2 = queue.poll();
        int sum = num1 + num2;

        queue.add(sum);
        result += sum;
      }

      bw.write(result + System.lineSeparator());
      bw.flush();
    }
  }
}
