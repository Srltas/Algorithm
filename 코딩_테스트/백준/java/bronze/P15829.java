package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P15829 {

    static final int r = 31;
    static final int M = 1234567891;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());

        long result = 0;
        char[] A = br.readLine().toCharArray();
        for (int i = 0; i < L; i++) {
            result += ((A[i] - 96) * Math.pow(r, i)) % M;
        }

        System.out.println(result % M);
    }
}
