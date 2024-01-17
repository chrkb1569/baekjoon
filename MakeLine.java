import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.Queue;

public class MakeLine {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int person = Integer.parseInt(st.nextToken());
        int relation = Integer.parseInt(st.nextToken());

        int[] resultArr = initResultArr(person);
        List<List<Integer>> resultList = initResultList(resultArr);

        for(int i = 0; i < relation; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int frontPerson = Integer.parseInt(st.nextToken());
            int backPerson = Integer.parseInt(st.nextToken());

            resultList.get(frontPerson).add(backPerson);
            resultArr[backPerson]++;
        }

        topologicalSort(resultList, resultArr);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int[] initResultArr(int length) {
        return new int[length + 1];
    }

    private static List<List<Integer>> initResultList(int[] resultArr) {
        List<List<Integer>> resultList = new ArrayList<>();

        for(int i = 0; i < resultArr.length; i++) {
            resultList.add(new ArrayList<>());
        }

        return resultList;
    }

    private static void topologicalSort(List<List<Integer>> resultList, int[] resultArr) {
        Queue<Integer> resultQueue = new LinkedList<>();

        for(int i = 1; i < resultArr.length; i++) {
            if(resultArr[i] == 0) resultQueue.add(i);
        }

        while(!resultQueue.isEmpty()) {
            int currentNode = resultQueue.poll();

            sb.append(currentNode).append(" ");

            for(int index : resultList.get(currentNode)) {
                resultArr[index]--;

                if(resultArr[index] == 0) resultQueue.offer(index);
            }
        }
    }
}
