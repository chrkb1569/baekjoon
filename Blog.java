import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Blog {
    private static int[] resultArr;
    private static int maxValue;
    private static int maxCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        int length = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(length);
        mkResultArr(st);

        findMaxValue(day);

        printResult();
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        resultArr[0] = Integer.parseInt(st.nextToken());

        for(int i = 1; i < resultArr.length; i++) {
            resultArr[i] = resultArr[i-1] + Integer.parseInt(st.nextToken());
        }
    }

    private static void findMaxValue(int day) {
        maxValue = resultArr[day-1];
        maxCount = 1;

        for(int i = day; i < resultArr.length; i++) {
            int value = resultArr[i] - resultArr[i - day];

            if(value == maxValue) {
                maxCount++;
                continue;
            }

            if(value > maxValue) {
                maxValue = value;
                maxCount = 1;
            }
        }
    }

    private static void printResult() {
        if(maxValue == 0) {
            System.out.println("SAD");
            return;
        }
        System.out.println(maxValue);
        System.out.println(maxCount);
    }
}
