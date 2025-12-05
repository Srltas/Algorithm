import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            List<Integer> widthList = new ArrayList<>();
            List<Integer> heightList = new ArrayList<>();

            widthList.add(0);
            widthList.add(width);
            heightList.add(0);
            heightList.add(height);

            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int type = Integer.parseInt(st.nextToken());
                int position = Integer.parseInt(st.nextToken());

                if (type == 0) {
                    heightList.add(position);
                } else {
                    widthList.add(position);
                }
            }

            Collections.sort(widthList);
            Collections.sort(heightList);

            int maxW = 0;
            for (int i = 1; i < widthList.size(); i++) {
                int gap = widthList.get(i) - widthList.get(i - 1);
                if (gap > maxW) {
                    maxW = gap;
                }
            }

            int maxH = 0;
            for (int i = 1; i < heightList.size(); i++) {
                int gap = heightList.get(i) - heightList.get(i - 1);
                if (gap > maxH) {
                    maxH = gap;
                }
            }
            System.out.println(maxW * maxH);
        }
    }
}
