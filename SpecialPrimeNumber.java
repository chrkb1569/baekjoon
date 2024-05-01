import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SpecialPrimeNumber {
    private static final StringBuffer sb = new StringBuffer();
    private static final int[] primeArr = {2, 3, 5, 7};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if(N == 1) {
            for(int primeNumber : primeArr) {
                sb.append(primeNumber).append("\n");
            }

            System.out.println(sb);
            return;
        }

        int[] valueArr = new int[N];

        for(int index = 0; index < primeArr.length; index++) {
            valueArr[0] = primeArr[index];
            dfs(valueArr, 1, N);
        }

        System.out.println(sb);
    }

    private static void dfs(int[] resultArr, int current, int target) {
        if(current == target) {
            sb.append(convertToValue(resultArr, target)).append("\n");
            return;
        }

        for(int nextNumber = 0; nextNumber <= 9; nextNumber++) {
            resultArr[current] = nextNumber;

            if(checkValidation(resultArr, current + 1)) {
//                System.out.println(convertToValue(resultArr, current + 1));
                dfs(resultArr, current + 1, target);
            }
        }
    }

    private static boolean checkValidation(int[] resultArr, int length) {
        int convertedValue = convertToValue(resultArr, length);

        for(int number = 2; number <= Math.sqrt(convertedValue); number++) {
            if(convertedValue % number == 0) return false;
        }

        return true;
    }

    private static int convertToValue(int[] resultArr, int length) {
        int value = 0;
        int mul = (int) Math.pow(10, length-1);

        for(int index = 0; index < length; index++) {
            value += mul * resultArr[index];
            mul /= 10;
        }

        return value;
    }
}
