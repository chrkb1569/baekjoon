import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Setting_Router {
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int house = Integer.parseInt(st.nextToken());
        int router = Integer.parseInt(st.nextToken());

        initResultArr(house);

        for(int i = 0; i < house; i++) {
            resultArr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(resultArr);

        int searchResult = binarySearch(router);

        System.out.println(searchResult);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static int binarySearch(int router) {
        int low = 1;
        int high = resultArr[resultArr.length-1] - resultArr[0] + 1;

        while(low < high) {
            int mid = (low + high) / 2;

            if(checkValidation(mid, router)) {
                high = mid;
                continue;
            }

            low = mid + 1;
        }

        return low - 1;
    }

    private static boolean checkValidation(int length, int router) {
        int count = 1;
        int preValue = resultArr[0];

        for(int i = 1; i < resultArr.length; i++) {
            int gap = resultArr[i] - preValue;

            if(gap >= length) {
                preValue = resultArr[i];
                count++;
            }
        }

        return count < router;
    }
}
