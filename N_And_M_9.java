import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class N_And_M_9 {
    private static Set<String> resultSet = new LinkedHashSet<>();
    private static int[] resultArr;
    private static int[] arr;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int length = Integer.parseInt(st.nextToken());
        int quantity = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(length);
        mkResultArr(st);

        Arrays.sort(resultArr);

        arr = new int[quantity];
        boolean[] visitArr = new boolean[length];

        getPermutation(visitArr, 0, quantity);

        System.out.println(getResultString());
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

    private static void getPermutation(boolean[] visit, int current, int target) {
        if(current == target) {
            String permutationResult = parseToString();
            resultSet.add(permutationResult);
            return;
        }

        for(int i = 0; i < visit.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                arr[current] = resultArr[i];
                getPermutation(visit, current + 1, target);
                visit[i] = false;
            }
        }
    }

    private static String parseToString() {
        StringBuilder sb = new StringBuilder();

        for(int value : arr) {
            sb.append(value).append(" ");
        }

        return sb.toString();
    }

    private static String getResultString() {
        StringBuilder sb = new StringBuilder();

        for(String value : resultSet) {
            sb.append(value).append("\n");
        }

        return sb.toString();
    }
}
