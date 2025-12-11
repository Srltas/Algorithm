import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            int[] array = br.lines()
                .limit(N)
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

            long result = IntStream.range(0, N)
                .mapToLong(i -> Math.abs(array[i] - (i + 1)))
                .sum();
            System.out.println(result);
        }
    }
}
