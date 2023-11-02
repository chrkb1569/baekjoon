import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.Queue;

public class EfficientHacking {
    private static List<Integer>[] resultList;
    private static int[] bfsArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int maxValue = Integer.MIN_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int computer = Integer.parseInt(st.nextToken());
        int links = Integer.parseInt(st.nextToken());

        resultList = new ArrayList[computer + 1];
        bfsArr = new int[computer + 1];

        for(int i = 1; i < bfsArr.length; i++) {
            resultList[i] = new ArrayList<>();
        }

        for(int i = 0; i < links; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            resultList[from].add(to);
        }

        for(int i = 1; i < resultList.length; i++) {
            bfs(i);
        }

        for(int i = 1; i < resultList.length; i++) {
            maxValue = Math.max(maxValue, bfsArr[i]);
        }

        for(int i = 1; i < bfsArr.length; i++) {
            if(bfsArr[i] == maxValue) {
                bw.write(i + " ");
            }
        }

        bw.flush();
        bw.close();
    }

    public static void bfs(int start) {
        Queue<Integer> resultQueue = new LinkedList<>();
        resultQueue.offer(start);
        boolean[] visit = new boolean[resultList.length];
        visit[start] = true;

        while(!resultQueue.isEmpty()) {
            int value = resultQueue.poll();

            for(int index : resultList[value]) {
                if(!visit[index]) {
                    visit[index] = true;
                    bfsArr[index]++;
                    resultQueue.offer(index);
                }
            }
        }
    }
}
