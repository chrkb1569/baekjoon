import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class MusicProgram {
    private static List<List<Integer>> outputList;
    private static int[] visitArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 가수의 수
        int M = Integer.parseInt(st.nextToken()); // PD의 수

        initResultList(N);
        initVisitArr(N);

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultList(st);
        }

        String singerSequence = getSingerSequence();

        System.out.println(singerSequence);
    }

    private static void initResultList(int length) {
        outputList = new ArrayList<>(length + 1);

        for(int index = 0; index <= length; index++) {
            outputList.add(new ArrayList<>());
        }
    }

    private static void initVisitArr(int length) {
        visitArr = new int[length + 1];
    }

    private static void mkResultList(StringTokenizer st) {
        st.nextToken();

        int preIndex = Integer.parseInt(st.nextToken());

        for(;st.hasMoreTokens();) {
            int postIndex = Integer.parseInt(st.nextToken());

            outputList.get(preIndex).add(postIndex);
            visitArr[postIndex]++;
            preIndex = postIndex;
        }
    }

    private static String getSingerSequence() {
        Queue<Integer> resultQueue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for(int index = 1; index < visitArr.length; index++) {
            if(visitArr[index] == 0) resultQueue.offer(index);
        }

        while(!resultQueue.isEmpty()) {
            int index = resultQueue.poll();

            sb.append(index).append("\n");

            for(int nextIndex : outputList.get(index)) {
                visitArr[nextIndex]--;

                if(visitArr[nextIndex] == 0) resultQueue.add(nextIndex);
            }
        }

        for(int index = 1; index < visitArr.length; index++) {
            if(visitArr[index] != 0) return "0";
        }

        return sb.toString();
    }
}