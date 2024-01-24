import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class PlaceCard {
    private static Set<String> resultSet = new HashSet<>();
    private static String[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        initResultArr(N);

        for(int i = 0; i < N; i++) {
            resultArr[i] = br.readLine();
        }

        boolean[] visitArr = new boolean[N];
        String[] valueArr = new String[K];

        getPermutation(visitArr, valueArr, 0, K);

        System.out.println(resultSet.size());
    }

    private static void initResultArr(int length) {
        resultArr = new String[length];
    }

    private static void getPermutation(boolean[] visit, String[] arr, int current, int target) {
        if(current == target) {
            String result = getResult(arr);
            resultSet.add(result);
            return;
        }

        for(int i = 0; i < visit.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                arr[current] = resultArr[i];
                getPermutation(visit, arr, current + 1, target);
                visit[i] = false;
            }
        }
    }

    private static String getResult(String[] arr) {
        StringBuilder sb = new StringBuilder();

        for(String value : arr) {
            sb.append(value);
        }

        return sb.toString();
    }
}
