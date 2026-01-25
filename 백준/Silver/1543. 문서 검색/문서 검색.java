import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        int count = 0;
        int startIndex = 0;
        while ((startIndex = a.indexOf(b, startIndex)) != -1) {
            count++;
            startIndex += b.length();
        }
        System.out.println(count);
    }
}
