package level2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P42577 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(",");

        boolean flag = true;
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String s : str) {
            map.put(s, 0);
        }

        for (String s : str) {
            for (int i = 0; i < s.length(); i++) {
                if (map.containsKey(s.substring(0, i))) {
                    flag = false;
                }
            }
        }
        

        System.out.println(flag);
    }
}
