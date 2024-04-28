import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class BipartiteGraph {
    private static List<List<Integer>> resultList;
    private static int[] colorArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine()); // testCase

        for(int testCase = 0; testCase < K; testCase++) {
            st = new StringTokenizer(br.readLine(), " ");

            int V = Integer.parseInt(st.nextToken()); // node
            int E = Integer.parseInt(st.nextToken()); // edge

            initResultList(V);
            initColorArr(V);

            for(int edge = 0; edge < E; edge++) {
                st = new StringTokenizer(br.readLine(), " ");

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                resultList.get(u).add(v);
                resultList.get(v).add(u);
            }

            boolean isPartial = false;

            for(int nodeNumber = 1; nodeNumber < resultList.size(); nodeNumber++) {
                if(colorArr[nodeNumber] == 0) {
                    isPartial = checkValidation(nodeNumber, 1);
                }

                if(!isPartial) break;
            }

            if(!isPartial) {
                sb.append("NO").append("\n");
                continue;
            }

            sb.append("YES").append("\n");
        }

        System.out.println(sb);
    }

    private static void initResultList(int length) {
        resultList = new ArrayList<>(length + 1);

        for(int index = 0; index <= length; index++) {
            resultList.add(new LinkedList<>());
        }
    }

    private static void initColorArr(int length) {
        colorArr = new int[length + 1];
    }

    private static boolean checkValidation(int startNumber, int color) {
        Queue<Integer> resultQueue = new LinkedList<>();
        resultQueue.add(startNumber);

        colorArr[startNumber] = color;

        while(!resultQueue.isEmpty()) {
            int currentNumber = resultQueue.poll();

            for(int nextNumber : resultList.get(currentNumber)) {
                if(colorArr[currentNumber] == colorArr[nextNumber]) return false;

                if(colorArr[nextNumber] == 0) {
                    colorArr[nextNumber] = colorArr[currentNumber] * -1;
                    resultQueue.add(nextNumber);
                }
            }
        }

        return true;
    }
}