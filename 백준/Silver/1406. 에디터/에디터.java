import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Character> editor = new LinkedList<>();
        for (char c : br.readLine().toCharArray()) editor.add(c);

        ListIterator<Character> iter = editor.listIterator();
        while (iter.hasNext()) iter.next();

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String command = br.readLine();
            char type = command.charAt(0);

            switch (type) {
                case 'L':
                    if (iter.hasPrevious()) iter.previous();
                    break;
                case 'D':
                    if (iter.hasNext()) iter.next();
                    break;
                case 'B':
                    if (iter.hasPrevious()) {
                        iter.previous();
                        iter.remove();
                    }
                    break;
                case 'P':
                    char t = command.charAt(2);
                    iter.add(t);
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char s : editor) {
            sb.append(s);
        }
        System.out.println(sb);
    }
}
