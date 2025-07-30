import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int count;
    static String st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            st = br.readLine();
            count = 0;
            System.out.println(isPalindrome(0, st.length() - 1) + " " + count);
        }
    }

    public static int isPalindrome(int r, int l) {
        count++;
        if (r >= l) {
            return 1;
        }else if(st.charAt(r) != st.charAt(l)) {
            return 0;
        }else {
            return isPalindrome(r + 1, l - 1);
        }

    }
}
