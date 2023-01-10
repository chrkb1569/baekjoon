import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sequence_Number {

    public static int[] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int component = Integer.parseInt(st.nextToken());
        int number = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(component);
        mkResultArr(st);

        System.out.println(findMaxValue(number));
    }

    public static int findMaxValue(int number) {
        int sum = 0;

        for(int rp = 0; rp < number; rp++) {
            sum += resultArr[rp];
        }

        int maxValue = sum;

        for(int rp = number; rp < resultArr.length; rp++) {
            sum += (resultArr[rp] - resultArr[rp - number]);

            maxValue = Math.max(maxValue, sum);
        }

        return maxValue;
    }

    public static void mkResultArr(StringTokenizer st) {

        int numValue = 0;

        for(;st.hasMoreTokens();) {
            resultArr[numValue++] = Integer.parseInt(st.nextToken());
        }
    }

    public static void initResultArr(int number) {
        resultArr = new int[number];
    }
}
