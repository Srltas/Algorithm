package silver;

import java.util.Scanner;

public class P1541 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String example = sc.nextLine();
        String[] str = example.split("-");

        int result = 0;
        for (int i = 0; i < str.length; i++) {
            int temp = sum(str[i]);

            if (i == 0) {
                result += temp;
            } else {
                result -= temp;
            }
        }
        System.out.println(result);
    }

    private static int sum(String a) {
        int num = 0;
        String[] temp = a.split("[+]");

        for (int i = 0; i < temp.length; i++) {
            num += Integer.parseInt(temp[i]);
        }
        
        return num;
    }
}
