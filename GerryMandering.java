import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class GerryMandering {
    private static List<List<Integer>> resultList = new ArrayList<>();
    private static int[] resultArr;
    private static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        initResultList(N);
        mkResultArr(st);

        for(int index = 1; index <= N; index++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultList(st, index);
        }

        for(int target = 1; target <= N/2; target++) {
            boolean[] visitArr = new boolean[N];

            getPermutation(visitArr, 0, target);
        }

        printResult();
    }

    private static void initResultArr(int length) {
        resultArr = new int[length + 1];
    }

    private static void initResultList(int length) {
        resultList = new ArrayList<>(length+1);

        for(int index = 0; index <= length; index++) {
            resultList.add(new ArrayList<>());
        }
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 1;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mkResultList(StringTokenizer st, int index) {
        st.nextToken();

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());
            resultList.get(index).add(value);
        }
    }

    private static void getPermutation(boolean[] visitArr, int current, int target) {
        if(current == target) {

            if(isLinked(visitArr, true) && isLinked(visitArr, false)) {
                int trueSum = getTypeSum(visitArr, true);
                int falseSum = getTypeSum(visitArr, false);

                minValue = Math.min(minValue, Math.abs(trueSum - falseSum));
            }
            return;
        }

        for(int i = 0; i < visitArr.length; i++) {
            if(!visitArr[i]) {
                visitArr[i] = true;
                getPermutation(visitArr, current + 1, target);
                visitArr[i] = false;
            }
        }
    }

    private static boolean isLinked(boolean[] visitArr, boolean type) {
        List<Integer> linkList = new ArrayList<>();
        Queue<Integer> resultQueue = new LinkedList<>();

        int startNumber = 0;

        for(int index = 0; index < visitArr.length; index++) {
            if(visitArr[index] == type) {
                startNumber = index + 1;
                break;
            }
        }

        resultQueue.add(startNumber);
        linkList.add(startNumber);

        while(!resultQueue.isEmpty()) {
            int currentNumber = resultQueue.poll();

            for(int nextNumber : resultList.get(currentNumber)) {
                if(visitArr[nextNumber-1] != visitArr[currentNumber-1]) continue;
                if(linkList.contains(nextNumber)) continue;

                linkList.add(nextNumber);
                resultQueue.add(nextNumber);
            }
        }

        for(int index = 0; index < visitArr.length; index++) {
            if(visitArr[index] != type) continue;
            if(!linkList.contains(index+1)) return false;
        }

        return true;
    }

    private static int getTypeSum(boolean[] visitArr, boolean type) {
        int sum = 0;

        for(int index = 0; index < visitArr.length; index++) {
            if(visitArr[index] == type) {
                sum += resultArr[index+1];
            }
        }

        return sum;
    }

    private static void printResult() {
        if(minValue == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(minValue);
    }
}
