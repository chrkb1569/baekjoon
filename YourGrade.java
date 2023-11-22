import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class YourGrade {
    public static Map<String, Double> gradeMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        double scoreSum = 0;
        double totalSum = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        initGradeMap();

        for(int i = 0; i < 20; i++) {
            String[] infoArr = br.readLine().split(" ");

            double score = Double.parseDouble(infoArr[1]);
            String grade = infoArr[2];

            if(grade.equals("P")) continue;

            scoreSum += score;
            totalSum += (score * gradeMap.get(grade));
        }

        System.out.println(totalSum / scoreSum);
    }

    public static void initGradeMap() {
        gradeMap.put("A+", 4.5);
        gradeMap.put("A0", 4.0);
        gradeMap.put("B+", 3.5);
        gradeMap.put("B0", 3.0);
        gradeMap.put("C+", 2.5);
        gradeMap.put("C0", 2.0);
        gradeMap.put("D+", 1.5);
        gradeMap.put("D0", 1.0);
        gradeMap.put("F", 0.0);
    }
}
