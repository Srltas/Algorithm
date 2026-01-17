import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        List<String> list1 = makeSet(str1.toUpperCase());
        List<String> list2 = makeSet(str2.toUpperCase());
        
        if (list1.size() == 0 && list2.size() == 0) {
            return 65536;
        }
        
        List<String> intersection = new ArrayList<>();
        List<String> copy2 = new ArrayList<>(list2);
        
        for (String s : list1) {
            if (copy2.contains(s)) {
                intersection.add(s);
                copy2.remove(s);
            }
        }
        
        double intersectionSize = intersection.size();
        double unionSize = list1.size() + list2.size() - intersectionSize;
        
        double jaccard = intersectionSize / unionSize;
        
        return (int) (jaccard * 65536);
    }
    
    private List<String> makeSet(String str) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + 1);
            
            if (isAlphabet(c1) && isAlphabet(c2)) {
                list.add("" + c1 + c2);
            }
        }
        return list;
    }
    
    private boolean isAlphabet(char c) {
        return c >= 65 && c <= 90;
    }
}