import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PartialSum {

    public static int[] resultArr;
    public static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int length = Integer.parseInt(st.nextToken());
        int number = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        initResultArr(length);
        mkResultArr(st);

        getCount(number);
        printResult();
    }

    public static void printResult() {
        if(minCount == Integer.MAX_VALUE) System.out.println("0");
        else System.out.println(minCount);
    }

    public static void getCount(int number) {
        int rp = 0, lp = 0;
        int count = 0, sum = 0;

        while(rp < resultArr.length) {
            int value = resultArr[rp];

            if(sum + value < number) {
                sum += resultArr[rp];
                rp++;
                count++;
                continue;
            }

            if(sum + value >= number && sum < number) {
                sum += resultArr[rp];
                count++;
                minCount = Math.min(minCount, count);
            }

            while(lp <= rp && sum >= number) {
                sum -= resultArr[lp];
                count--;
                lp++;
                if(sum >= number) minCount = Math.min(minCount, count);
            }
            rp++;
        }
    }

    public static void mkResultArr(StringTokenizer st) {
        int numValue = 0;

        for(;st.hasMoreTokens();) {
            resultArr[numValue++] = Integer.parseInt(st.nextToken());
        }
    }

    public static void initResultArr(int length) {
        resultArr = new int[length];
    }
}
