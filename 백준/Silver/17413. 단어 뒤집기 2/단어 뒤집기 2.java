import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        StringBuilder result = new StringBuilder();
        StringBuilder word = new StringBuilder();
        boolean isTag = false;

        for (char c : str.toCharArray()) {
            if (c == '<') {
                if (word.length() > 0) {
                    result.append(word.reverse());
                    word.setLength(0);
                }
                isTag = true;
                result.append(c);
            } else if (c == '>') {
                isTag = false;
                result.append(c);
            } else if (isTag) {
                result.append(c);
            } else {
                if (c == ' ') {
                    result.append(word.reverse());
                    result.append(' ');
                    word.setLength(0);
                } else {
                    word.append(c);
                }
            }
        }
        if (word.length() > 0) {
            result.append(word.reverse());
        }
        System.out.println(result);
    }
}
