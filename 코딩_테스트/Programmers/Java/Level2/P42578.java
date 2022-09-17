package level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class P42578 {
    public static void main(String[] args) {
        String[][] str = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

        Map<String, List> map = new HashMap<String, List>();
        for (String[] s : str) {
            if (map.keySet().contains(s[1])) {
                List l = map.get(s[1]);
                l.add(s[0]);
                map.put(s[1], l);
            } else {
                List<String> l = new ArrayList<String>();
                l.add(s[0]);
                map.put(s[1], l);
            }
        }

        int result = 1;
        Set<String> keys = map.keySet();
        for (String key : keys) {
            result *= map.get(key).size() + 1;
        }

        System.out.println(result - 1);
    }
}
