import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class ACM_Craft {

    private static int[] costArr;
    private static int[] linkArr;
    private static List<List<Integer>> linkList;

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // TestCase

        for(int testCase = 0; testCase < T; testCase++) {
            st = new StringTokenizer(br.readLine(), " ");

            int N = Integer.parseInt(st.nextToken()); // 건물의 수
            int K = Integer.parseInt(st.nextToken()); // 연결 규칙

            st = new StringTokenizer(br.readLine(), " ");

            initCostArr(N);
            initLinkArr(N);
            initLinkList(N);

            mkCostArr(st);

            for(int link = 0; link < K; link++) {
                st = new StringTokenizer(br.readLine(), " ");

                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                linkArr[Y]++;
                linkList.get(X).add(Y);
            }

            int W = Integer.parseInt(br.readLine()); // 건설 최소 비용을 구하고자 하는 건물 번호

            List<Integer> buildingSequence = findBuildingSequence(W); // 방문할 건물의 순서

            long cost = getCost(buildingSequence, W);

            sb.append(cost).append("\n");
        }

        System.out.println(sb);
    }

    private static void initCostArr(int length) {
        costArr = new int[length + 1];
    }

    private static void initLinkArr(int length) {
        linkArr = new int[length + 1];
    }

    private static void initLinkList(int length) {
        linkList = new ArrayList<>();

        for(int index = 0; index <= length; index++) {
            linkList.add(new ArrayList<>());
        }
    }

    private static void mkCostArr(StringTokenizer st) {
        int index = 1;

        for(;st.hasMoreTokens();) {
            costArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static List<Integer> findBuildingSequence(int target) {
        Queue<Integer> resultQueue = new LinkedList<>();
        List<Integer> buildingSequence = new ArrayList<>();

        for(int index = 1; index < linkArr.length; index++) {
            int value = linkArr[index];

            if(value != 0) continue;

            resultQueue.add(index);
        }

        while(!resultQueue.isEmpty()) {
            int currentNumber = resultQueue.poll();

            buildingSequence.add(currentNumber);

            if(currentNumber == target) break;

            for(int nextNumber : linkList.get(currentNumber)) {
                int nextValue = --linkArr[nextNumber];

                if(nextValue != 0) continue;
                resultQueue.add(nextNumber);
            }
        }

        return buildingSequence;
    }

    private static long getCost(List<Integer> buildingSequence, int target) {
        long[] resultArr = new long[costArr.length];

        for(int visitNumber : buildingSequence) {
            long currentCost = Math.max(costArr[visitNumber], resultArr[visitNumber]);

            for(int nextNumber : linkList.get(visitNumber)) {
                resultArr[nextNumber] = Math.max(resultArr[nextNumber], currentCost + costArr[nextNumber]);
            }
        }

        if(buildingSequence.contains(target) && resultArr[target] == 0) return costArr[target];
        return resultArr[target];
    }
}