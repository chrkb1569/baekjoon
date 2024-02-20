import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Provide_Snack {
    private static int[] snackArr;
    private static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int person = Integer.parseInt(st.nextToken());
        int snack = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        initSnackArr(snack);
        mkSnackArr(st);

        int maxLength = getMaximumSnackLength(person);

        System.out.println(maxLength);
    }

    private static void initSnackArr(int length) {
        snackArr = new int[length];
    }

    private static void mkSnackArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());
            snackArr[index++] = value;

            maxValue = Math.max(maxValue, value);
        }

        Arrays.sort(snackArr);
    }

    private static int getMaximumSnackLength(int person) {
        int low = 1;
        int high = maxValue;
        int maxLength = 0;

        while(low <= high) {
            int snackLength = (low + high) / 2;

            long personCount = 0;
            boolean flag = false;

            for(int index = 0; index < snackArr.length; index++) {
                personCount += (snackArr[index] / (long)snackLength);

                if(personCount >= person) {
                    maxLength = Math.max(maxLength, snackLength);
                    flag = true;
                    break;
                }
            }

            if(flag) {
                low = snackLength + 1;
                continue;
            }

            high = snackLength - 1;
        }


        return maxLength;
    }
}
