import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Two_Liquid {

    public static long[] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int component = Integer.parseInt(br.readLine());

        initResultArr(component);

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        mkResultArr(st);

        Arrays.sort(resultArr);

        printResult();
    }

    public static void printResult() {
        long minValue = Long.MAX_VALUE;
        int lp = 0, rp = resultArr.length-1;
        long lowResult = resultArr[lp];
        long highResult = resultArr[rp];

        while(lp < rp) {

            long lowValue = resultArr[lp];
            long highValue = resultArr[rp];

            long sum = lowValue + highValue;
            long absValue = Math.abs(sum);

            if(absValue < minValue) {
                minValue = absValue;
                lowResult = lowValue;
                highResult = highValue;
            }

            if(sum > 0) {
                rp--;
                continue;
            }
            lp++;
        }

        System.out.println(lowResult + " " + highResult);
    }

    public static void mkResultArr(StringTokenizer st) {
        int numValue = 0;

        for(;st.hasMoreTokens();) {
            resultArr[numValue++] = Long.parseLong(st.nextToken());
        }
    }

    public static void initResultArr(int value) {
        resultArr = new long[value];
    }
}
