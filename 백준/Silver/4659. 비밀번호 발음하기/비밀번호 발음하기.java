import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    final static String[] vowel = {"a", "e", "i", "o", "u"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String password;
        while (!(password = br.readLine()).equals("end")) {
            if (isAcceptable(password)) {
                sb.append("<").append(password).append("> is acceptable.\n");
            } else {
                sb.append("<").append(password).append("> is not acceptable.\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean isAcceptable(String s) {
        boolean hasVowel = false;
        int vowelCount = 0;
        int consonantCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            boolean currIsVowel = isVowel(current);

            if (currIsVowel) hasVowel = true;

            if (currIsVowel) {
                vowelCount++;
                consonantCount = 0;
            } else {
                consonantCount++;
                vowelCount = 0;
            }

            if (vowelCount >= 3 || consonantCount >= 3) return false;

            if (i > 0) {
                char prev = s.charAt(i - 1);
                if (current == prev && current != 'e' && current != 'o') {
                    return false;
                }
            }
        }

        return hasVowel;
    }

    private static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }
}
