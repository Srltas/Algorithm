import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N == 1){
            System.out.println(1);
            return;
        }

        int c = 1;
        int t = 7;
        while (N > t) {
            c++;
            t += c * 6;
        }
        System.out.println(c + 1);
    }
}
