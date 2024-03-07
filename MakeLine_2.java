import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakeLine_2 {
    private static int[] resultArr;
    private static StringBuilder sb = new StringBuilder();
    private static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultArr(st);

        boolean[] visitArr = new boolean[N];
        int[] valueArr = new int[N];

        getPermutation(visitArr, valueArr, 0, N);

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

    private static void getPermutation(boolean[] visitArr, int[] valueArr, int current, int target) {
        if(flag) return;
        if(current == target) {
            if(!checkValidation(valueArr)) return;
            flag = true;
            mkResultString(valueArr);
            return;
        }

        for(int index = 0; index < visitArr.length; index++) {
            if(!visitArr[index]) {
                visitArr[index] = true;
                valueArr[current] = index + 1;
                getPermutation(visitArr, valueArr, current + 1, target);
                visitArr[index] = false;
            }
        }
    }

    private static boolean checkValidation(int[] valueArr) {
        for(int index = 0; index < resultArr.length; index++) {
            int personNumber = valueArr[index];
            int tallPersonCount = resultArr[personNumber-1];

            int tallCount = 0;

            for(int i = 0; i < index; i++) {
                if(valueArr[i] > personNumber) tallCount++;
            }

            if(tallCount != tallPersonCount) return false;
        }

        return true;
    }

    private static void mkResultString(int[] valueArr) {
        for(int value : valueArr) {
            sb.append(value).append(" ");
        }
    }
}