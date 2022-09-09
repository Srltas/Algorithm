package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1259 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        String sb = bf.readLine();
        while (!sb.equals("0")) {
            String sbReverse = new StringBuffer(sb).reverse().toString();
            if (sb.equals(sbReverse)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
            sb = bf.readLine();
        }
    }
}
