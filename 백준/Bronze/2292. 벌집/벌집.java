import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 1;
        int count = 0;
        int N = Integer.parseInt(br.readLine());
        while (num < N) {
            count++;
            num = num + (count * 6);
        }
        System.out.println(count+1);
    }
}
