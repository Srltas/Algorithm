package bronze;

import java.util.Scanner;

public class P2920 {
    public static void main(String[] args) {

        String asArray = "1 2 3 4 5 6 7 8";
        String deArray = "8 7 6 5 4 3 2 1";

        String as = "ascending";
        String de = "descending";
        String mx = "mixed";

        Scanner sc = new Scanner(System.in);
        String st = new String(sc.nextLine());
        
        if (st.equals(asArray)) {
            System.out.println(as);
        } else if (st.equals(deArray)) {
          System.out.println(de);  
        } else {
            System.out.println(mx);
        }
    }
}
