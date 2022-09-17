package level2;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class P1845 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(",");
        int[] nums = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            nums[i] = Integer.parseInt(str[i]);
        }

        Set num = new HashSet<Integer>();
        for (int n : nums) {
            num.add(n);
        }

        int count = nums.length / 2;
        int setSize = num.size();

        if (setSize > count) {
            System.out.println(count);
        } else {
            System.out.println(setSize);
        }
    }
}