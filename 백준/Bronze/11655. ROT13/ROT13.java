import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('A' <= c && c <= 'Z') {
                c = (char) ('A' + (c - 'A' + 13)  % 26);
            } else if ('a' <= c && c <= 'z') {
                c = (char) ('a' + (c - 'a' + 13) % 26);
            }
            sb.append(c);
        }
        System.out.println(sb);
    }
}
