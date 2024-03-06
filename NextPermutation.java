import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NextPermutation {
    private static int[] resultArr;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultArr(st);

        if(checkValidation()) {
            for(int value : resultArr) {
                sb.append(value).append(" ");
            }
        }
        else {
            sb.append(-1);
        }

        System.out.println(sb);
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

    private static boolean checkValidation() {
        int index = resultArr.length - 1;
        int otherIndex = resultArr.length - 1;

        while(index > 0 && resultArr[index - 1] >= resultArr[index]) index--;

        if(index <= 0) return false;

        while(resultArr[index - 1] >= resultArr[otherIndex]) otherIndex--;

        int tmp = resultArr[otherIndex];
        resultArr[otherIndex] = resultArr[index - 1];
        resultArr[index - 1] = tmp;

        otherIndex = resultArr.length - 1;

        while(index < otherIndex) {
            tmp = resultArr[otherIndex];
            resultArr[otherIndex] = resultArr[index];
            resultArr[index] = tmp;

            index++;
            otherIndex--;
        }

        return true;
    }
}