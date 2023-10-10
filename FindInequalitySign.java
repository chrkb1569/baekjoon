import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FindInequalitySign {
    public static String[] resultArr;
    public static int[] numberArr = new int[10];
    public static long maxValue = Long.MIN_VALUE;
    public static long minValue = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] visit;

        int sign = Integer.parseInt(br.readLine());
        String[] signArr = br.readLine().split(" ");

        initNumberArr();
        initResultArr(sign);
        mkResultArr(signArr);
        visit = new boolean[10];

        getPermutation(visit, 0, sign + 1, "");

        printResult(sign);
    }

    public static void printResult(int sign) {
        if(maxValue / Math.pow(10, sign) < 1) System.out.println("0" + maxValue);
        else System.out.println(maxValue);
        if(minValue / Math.pow(10, sign) < 1) System.out.println("0" + minValue);
        else System.out.println(minValue);
    }

    public static void getPermutation(boolean[] visit, int now, int target, String value) {
        if(now == target) {
            String[] valueArr = value.split(" ");

            if(checkValidation(valueArr)) {
                long numberValue = Long.parseLong(value.replaceAll(" ", ""));
                maxValue = Math.max(maxValue, numberValue);
                minValue = Math.min(minValue, numberValue);
            }

            return;
        }

        for(int i = 0; i < 10; i++) {
            if(!visit[i]) {
                visit[i] = true;
                getPermutation(visit, now+1, target, value + numberArr[i] + " ");
                visit[i] = false;
            }
        }
    }

    public static boolean checkValidation(String[] valueArr) {
        for(int i = 0; i < resultArr.length; i++) {
            int num1 = Integer.parseInt(valueArr[i]);
            int num2 = Integer.parseInt(valueArr[i+1]);

            String sign = resultArr[i];

            if(sign.equals(">") && num1 < num2) return false;
            if(sign.equals("<") && num1 > num2) return false;
        }

        return true;
    }

    public static void mkResultArr(String[] signArr) {
        for(int i = 0; i < signArr.length; i++) {
            resultArr[i] = signArr[i];
        }
    }

    public static void initResultArr(int length) {
        resultArr = new String[length];
    }

    public static void initNumberArr() {
        for(int i = 0; i < 10; i++) {
            numberArr[i] = i;
        }
    }
}
