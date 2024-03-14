import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InsertOperator {
    private static int[] resultArr;
    private static int maxValue = Integer.MIN_VALUE;
    private static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultArr(st);

        st = new StringTokenizer(br.readLine(), " ");

        int[] operandArr = new int[4];
        int[] valueArr = new int[N-1];

        operandArr[0] = Integer.parseInt(st.nextToken()); // 덧셈
        operandArr[1] = Integer.parseInt(st.nextToken()); // 뺄셈
        operandArr[2] = Integer.parseInt(st.nextToken()); // 곱셈
        operandArr[3] = Integer.parseInt(st.nextToken()); // 나눗셈

        getPermutation(operandArr, valueArr, 0, N-1);

        System.out.println(maxValue);
        System.out.println(minValue);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void getPermutation(int[] operandArr, int[] valueArr, int current, int target) {
        if(current == target) {
            int resultValue = getResult(valueArr);

            maxValue = Math.max(maxValue, resultValue);
            minValue = Math.min(minValue, resultValue);
            return;
        }

        for(int index = 0; index < operandArr.length; index++) {
            if(operandArr[index] != 0) {
                operandArr[index]--;
                valueArr[current] = index;
                getPermutation(operandArr, valueArr, current + 1, target);
                operandArr[index]++;
            }
        }
    }

    private static int getResult(int[] valueArr) {
        int result = calculate(resultArr[0], resultArr[1], valueArr[0]);

        for(int index = 1; index < valueArr.length; index++) {
            int command = valueArr[index];
            int value = resultArr[index+1];

            result = calculate(result, value, command);
        }

        return result;
    }

    private static int calculate(int a, int b, int command) {
        if(command == 0) return a + b;
        if(command == 1) return a - b;
        if(command == 2) return a * b;
        return a / b;
    }
}
// 덧셈 뺄셈 곱셈 나눗셈
