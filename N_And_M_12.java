import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class N_And_M_12 {
    private static Set<String> resultSet = new LinkedHashSet<>();
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int length = Integer.parseInt(st.nextToken());
        int quantity = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(length);
        mkResultArr(st);

        int[] valueArr = new int[quantity];

        getPermutation(valueArr, 0, 0, quantity);

        printResult();
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(resultArr);
    }

    private static void getPermutation(int[] arr, int start, int current, int target) {
        if(current == target) {
            String convertedString = convertArrToString(arr);
            resultSet.add(convertedString);
            return;
        }

        for(int i = start; i < resultArr.length; i++) {
            arr[current] = resultArr[i];
            getPermutation(arr, i, current + 1, target);
        }
    }

    private static String convertArrToString(int[] arr) {
        StringBuilder sb = new StringBuilder();

        for(int value : arr) {
            sb.append(value).append(" ");
        }

        return sb.toString();
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();

        for(String value : resultSet) {
            sb.append(value).append("\n");
        }

        System.out.println(sb);
    }
}
