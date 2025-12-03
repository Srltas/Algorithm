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
            int N = Integer.parseInt(br.readLine());
            List<Student> students = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                int day = Integer.parseInt(st.nextToken());
                int month = Integer.parseInt(st.nextToken());
                int year = Integer.parseInt(st.nextToken());
                students.add(new Student(name, day, month, year));
            }
            Collections.sort(students);

            System.out.println(students.get(N - 1).name);
            System.out.println(students.get(0).name);
        }
    }

    static class Student implements Comparable<Student> {
        String name;
        int day;
        int month;
        int year;

        public Student(String name, int day, int month, int year) {
            this.name = name;
            this.day = day;
            this.month = month;
            this.year = year;
        }

        @Override
        public int compareTo(Student other) {
            if (this.year != other.year) return this.year - other.year;
            if (this.month != other.month) return this.month - other.month;
            return this.day - other.day;
        }
    }
}
