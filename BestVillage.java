import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BestVillage {

    private static int[] valueArr;
    private static List<List<Integer>> linkList;
    private static int[][] resultArr;
    private static boolean[] visitArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int root = 1;

        st = new StringTokenizer(br.readLine(), " ");

        initValueArr(N);
        initVisitArr(N);
        initResultArr(N);
        initLinkList(N);

        mkResultArr(st);

        for(int link = 0; link < N-1; link++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if(link == 0) root = from;

            linkList.get(from).add(to);
            linkList.get(to).add(from);
        }

        // resultArr[N][0] -> 우수마을로 선정되지 않은 경우
        // resultArr[N][1] -> 우수마을로 선정된 경우
        dfs(root);

        System.out.println(Math.max(resultArr[root][0], resultArr[root][1]));
    }

    private static void initValueArr(int length) {
        valueArr = new int[length + 1];
    }

    private static void initVisitArr(int length) {
        visitArr = new boolean[length + 1];
    }

    private static void initResultArr(int length) {
        resultArr = new int[length + 1][2];
    }

    private static void initLinkList(int length) {
        linkList = new ArrayList<>();

        for(int i = 0; i <= length; i++) {
            linkList.add(new ArrayList<>());
        }
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 1;

        for(;st.hasMoreTokens();) {
            valueArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void dfs(int currentNumber) {
        List<Integer> linkInfo = linkList.get(currentNumber);

        visitArr[currentNumber] = true;
        resultArr[currentNumber][1] = valueArr[currentNumber];

        int leafCount = 0;

        for(int childNumber : linkInfo) {
            if(visitArr[childNumber]) continue;

            leafCount++;
        }

        if(leafCount == 0) {
            resultArr[currentNumber][0] = 0; // 우수 마을이 되지 않는 경우
            resultArr[currentNumber][1] = valueArr[currentNumber]; // 우수 마을로 선정하는 경우
            return;
        }

        for(int childNumber : linkInfo) {
            if(visitArr[childNumber]) continue;

            dfs(childNumber);

            resultArr[currentNumber][0] += Math.max(resultArr[childNumber][0], resultArr[childNumber][1]);
            resultArr[currentNumber][1] += resultArr[childNumber][0];
        }
    }
}
