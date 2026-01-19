import java.io.*;
import java.util.*;

public class Main {
    private static final String[] croatia = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        for (String c : croatia) {
            str = str.replaceAll(c, "a");
        }
        System.out.println(str.length());
    }
}
