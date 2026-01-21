import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int num666 = 666;
        int count = 1;
        while (N > count) {
            num666++;
            if (String.valueOf(num666).contains("666")) {
                count++;
            }
        }
        System.out.println(num666);
    }
}
