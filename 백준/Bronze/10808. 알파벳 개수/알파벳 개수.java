import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] count = new int[26];
        for (char c : br.readLine().toCharArray()) count[c - 97]++;
        StringBuilder sb = new StringBuilder();
        for (int i : count) sb.append(i).append(" ");
        System.out.println(sb);
    }
}
