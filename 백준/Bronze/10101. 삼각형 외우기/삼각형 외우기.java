import java.io.*;

public class Main {
    private static final int TRIANGLE_SIDE_LENGTH = 60;
    private static final int TRIANGULAR_ANGLES_SUM = 180;
    public static void main(String[] args) throws IOException {
        try (
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int A = Integer.parseInt(br.readLine());
            int B = Integer.parseInt(br.readLine());
            int C = Integer.parseInt(br.readLine());

            int sum = A + B + C;
            if (A == TRIANGLE_SIDE_LENGTH 
                && B == TRIANGLE_SIDE_LENGTH 
                && C == TRIANGLE_SIDE_LENGTH) {
                bw.write("Equilateral");
            } else if (sum == TRIANGULAR_ANGLES_SUM
                && (A == B || A == C || B == C)) {
                bw.write("Isosceles");
            } else if (sum == TRIANGULAR_ANGLES_SUM) {
                bw.write("Scalene");
            } else {
                bw.write("Error");
            }
            bw.flush();
        }
    }
}