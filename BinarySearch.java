import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinarySearch {
    private static int[] awardArr;
    private static String[] nameArr;
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuffer sb = new StringBuffer();

        int N = Integer.parseInt(st.nextToken()); // award
        int M = Integer.parseInt(st.nextToken()); // characters

        initAwardArr(N);
        initNameArr(N);
        initResultArr(M);

        for(int award = 0; award < N; award++) {
            st = new StringTokenizer(br.readLine(), " ");

            String awardName = st.nextToken();
            int point = Integer.parseInt(st.nextToken());

            nameArr[award] = awardName;
            awardArr[award] = point;
        }

        for(int index = 0; index < M; index++) {
            resultArr[index] = Integer.parseInt(br.readLine());
        }

        for(int index = 0; index < resultArr.length; index++) {
            String award = convertToAward(resultArr[index]);
            sb.append(award).append("\n");
        }

        System.out.println(sb);
    }

    private static void initAwardArr(int length) {
        awardArr = new int[length];
    }

    private static void initNameArr(int length) {
        nameArr = new String[length];
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static String convertToAward(int value) {
        int low = 0;
        int high = awardArr.length - 1;

        String name = nameArr[0];

        while(low <= high) {
            int mid = (low + high) / 2;

            if(value <= awardArr[mid]) {
                name = nameArr[mid];
                high = mid - 1;
                continue;
            }

            low = mid + 1;
        }

        if(high == awardArr.length - 1) return nameArr[high];

        return name;
    }
}
