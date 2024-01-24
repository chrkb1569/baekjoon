import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AreaOfPolygon {
    private static long[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            resultArr[i][0] = x;
            resultArr[i][1] = y;
        }

        double area = getArea();

        System.out.printf("%.1f", area);
    }

    private static void initResultArr(int length) {
        resultArr = new long[length][2];
    }

    private static double getArea() {
        long plusSum = 0;
        long minusSum = 0;

        for(int i = 0; i < resultArr.length; i++) {
            plusSum += getPlusValue(i);
            minusSum += getMinusValue(i);
        }

        return Math.abs(plusSum - minusSum) / 2.0;
    }

    private static long getPlusValue(int index) {
        if(index == resultArr.length - 1) return resultArr[index][0] * resultArr[0][1];
        return resultArr[index][0] * resultArr[index+1][1];
    }

    private static long getMinusValue(int index) {
        if(index == resultArr.length - 1) return resultArr[index][1] * resultArr[0][0];
        return resultArr[index][1] * resultArr[index+1][0];
    }
}
