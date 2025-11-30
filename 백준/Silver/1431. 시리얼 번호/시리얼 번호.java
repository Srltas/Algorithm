import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            String[] serials = new String[N];

            for (int i = 0; i < N; i++) {
                serials[i] = br.readLine();
            }

            Arrays.sort(serials, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    if (s1.length() != s2.length()) {
                        return s1.length() - s2.length();
                    }

                    int sum1 = getDigitSum(s1);
                    int sum2 = getDigitSum(s2);

                    if (sum1 != sum2) {
                        return sum1 - sum2;
                    }

                    return s1.compareTo(s2);
                }
            });

            StringBuilder sb = new StringBuilder();
            for (String s : serials) {
                sb.append(s).append('\n');
            }
            System.out.println(sb);
        }
    }

    public static int getDigitSum(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                sum += c - '0';
            }
        }
        return sum;
    }
}
