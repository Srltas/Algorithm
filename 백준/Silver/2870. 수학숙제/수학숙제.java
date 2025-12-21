import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();

            for (char c : line.toCharArray()) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                } else {
                    if (sb.length() > 0) {
                        numbers.add(removeLeadingZeros(sb.toString()));
                        sb.setLength(0);
                    }
                }
            }

            if (sb.length() > 0) {
                numbers.add(removeLeadingZeros(sb.toString()));
            }
        }

        Collections.sort(numbers, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o1.length() - o2.length();
                }
                return o1.compareTo(o2);
            }
        });

        StringBuilder result = new StringBuilder();
        for (String num : numbers) {
            result.append(num).append('\n');
        }
        System.out.println(result);
    }

    private static String removeLeadingZeros(String input) {
        int i = 0;
        while (i < input.length() && input.charAt(i) == '0') {
            i++;
        }

        if (i == input.length()) return "0";
        return input.substring(i);
    }
}
