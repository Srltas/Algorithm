import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int k1 = -1, k2 = -1;
        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            if (name.equals("KBS1")) k1 = i;
            if (name.equals("KBS2")) k2 = i;
        }

        StringBuilder sb = new StringBuilder();
        buttons(sb, k1, 0);
        if (k1 > k2) k2++;
        buttons(sb, k2, 1);

        System.out.println(sb);
    }

    private static void buttons (StringBuilder sb, int current, int target) {
        for (int i = 0; i < current; i++) sb.append('1');
        for (int i = 0; i < current - target; i++) sb.append('4');
    }
}
