import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < N; i++) list.add(Integer.parseInt(br.readLine()));
            list.sort((o1, o2) -> Integer.compare(o2, o1));
            StringBuilder sb = new StringBuilder();
            for (Integer i : list) sb.append(i).append('\n');
            System.out.println(sb);
        }
    }
}