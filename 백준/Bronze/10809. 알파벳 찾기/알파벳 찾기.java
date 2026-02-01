import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] index = new int[26];
        Arrays.fill(index, -1);
        char[] arr = br.readLine().toCharArray();
        for (int i = 0; i < arr.length; i++) if (index[arr[i] - 97] == -1) index[arr[i] - 97] = i;
        StringBuilder sb = new StringBuilder();
        for (int i : index) sb.append(i).append(" ");
        System.out.println(sb);
    }
}
