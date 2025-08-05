import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
      int N = Integer.parseInt(br.readLine());

      Student[] students = new Student[N];
      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        students[i] = new Student(
            st.nextToken(),
            Integer.parseInt(st.nextToken()),
            Integer.parseInt(st.nextToken()),
            Integer.parseInt(st.nextToken()));
      }

      Arrays.sort(students);

      StringBuilder sb = new StringBuilder();
      for (Student student : students) {
        sb.append(student.name)
            .append(System.lineSeparator());
      }
      bw.write(sb.toString());
    }
  }

  static class Student implements Comparable<Student> {
    String name;
    int korean;
    int english;
    int math;

    public Student(String name, int korean, int english, int math) {
      this.name = name;
      this.korean = korean;
      this.english = english;
      this.math = math;
    }

    @Override
    public int compareTo(Student s) {
      return Comparator
          .comparingInt((Student st) -> -st.korean)
          .thenComparingInt(st -> st.english)
          .thenComparingInt((Student st) -> -st.math)
          .thenComparing(st -> st.name)
          .compare(this, s);
    }
  }
}
