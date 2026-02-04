import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();

        // 3의 배수가 되도록 앞에 0을 채워서 보정
        if (str.length() % 3 == 1) {
            str = "00" + str;
        } else if (str.length() % 3 == 2) {
            str = "0" + str;
        }

        for (int i = 0; i < str.length(); i += 3) {
            int num = (str.charAt(i) - '0') * 4
                + (str.charAt(i + 1) - '0') * 2
                + (str.charAt(i + 2) - '0');
            sb.append(num);
        }

        System.out.println(sb);
    }
}
