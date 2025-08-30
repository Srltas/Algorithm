import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int N;
    static List<Long> decreasing = new ArrayList<>(1023);

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());

            for (int i = 0; i <= 9; i++) {
                dfs(i, i);
            }

            Collections.sort(decreasing);

            if (N >= decreasing.size()) {
                System.out.println(-1);
            } else {
                System.out.println(decreasing.get(N));
            }
        }
    }

    static void dfs(long curr, int lastNum) {
        decreasing.add(curr);
        for (int next = 0; next < lastNum; next++) {
            dfs(curr * 10 + next, next);
        }
    }
}
