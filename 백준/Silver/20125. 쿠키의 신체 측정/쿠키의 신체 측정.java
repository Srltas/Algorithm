import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        char[][] cookie = new char[N][N];
        for (int i = 0; i < N; i++) cookie[i] = br.readLine().toCharArray();

        boolean found = false;
        int hr = 0, hc = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cookie[i][j] == '*') {
                    hr = i + 1;
                    hc = j;
                    found = true;
                    break;
                }
            }
            if (found) break;
        }

        int leftArm = 0, rightArm = 0, waist = 0, leftLeg = 0, rightLeg = 0;

        for (int j = hc - 1; j >= 0 && cookie[hr][j] == '*'; j--) leftArm++;
        for (int j = hc + 1; j < N && cookie[hr][j] == '*'; j++) rightArm++;

        int curRow = hr + 1;
        while (curRow < N && cookie[curRow][hc] == '*') {
            waist++;
            curRow++;
        }

        int legRow = curRow;
        for (int i = legRow; i < N && cookie[i][hc - 1] == '*'; i++) leftLeg++;
        for (int i = legRow; i < N && cookie[i][hc + 1] == '*'; i++) rightLeg++;

        StringBuilder sb = new StringBuilder();
        sb.append(hr + 1).append(" ").append(hc + 1).append('\n')
            .append(leftArm).append(" ").append(rightArm).append(" ")
            .append(waist).append(" ")
            .append(leftLeg).append(" ").append(rightLeg);

        System.out.println(sb);
    }
}
