import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int line = 1;
        while (N > line) {
            N -= line;
            line += 1;
        }

        int a, b;
        if (line % 2 == 0) {
            a = N;
            b = line - N + 1;
        } else {
            a = line - N + 1;
            b = N;
        }

        System.out.println(a + "/" + b);
    }
}
