import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int N = Integer.parseInt(br.readLine());

      int sum = 0;
      Map<Integer, Integer> count = new HashMap<>();
      List<Integer> numbers = new ArrayList<>();
      for (int i = 0; i < N; i++) {
        int num = Integer.parseInt(br.readLine());
        sum += num;
        numbers.add(num);
        count.put(num, count.getOrDefault(num, 0) + 1);
      }

      numbers.sort(Integer::compare);

      System.out.println(Math.round((double) sum / N));
      System.out.println(numbers.get(N / 2));

      List<Integer> values = new ArrayList<>(count.values());
      int maxValue = Collections.max(values);
      List<Integer> keys = new ArrayList<>();
      for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
        if (entry.getValue() == maxValue) {
          keys.add(entry.getKey());
        }
      }

      keys.sort(Integer::compareTo);

      if (keys.size() > 1) {
        System.out.println(keys.get(1));
      } else {
        System.out.println(keys.get(0));
      }
      System.out.println(numbers.get(numbers.size() - 1) - numbers.get(0));
    }
  }
}
