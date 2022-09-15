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

        char[] A = br.readLine().toCharArray();
        long result = 0;
        long pow = 1;
        for (int i = 0; i < L; i++) {
            result += (A[i] - 96) * pow % M;
            pow = pow * r % M;
        }

        System.out.println(result % M);
    }
}

/**
 * 50점을 받은 이유
 * 
 * Math.pow() 함수를 이용해서 31의 제곱수를 계산했을 때 overflow가 나기 때문
 * 모듈러 연산의 성질을 이용해서 overflow가 나지 않고 계산할 수 있다!
 * 분배법칙을 이용해서 (A * B) mod C = (A mod C * B mod C) mod C 로 정리할 수 있다.
 */