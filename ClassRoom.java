import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ClassRoom {
    private static int[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            resultArr[i][0] = startTime;
            resultArr[i][1] = endTime;
        }

        Arrays.sort(resultArr, (o1, o2) -> (o1[0] != o2[0])? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]));

        int classRoomCount = getClassRoomCount();

        System.out.println(classRoomCount);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length][2];
    }

    private static int getClassRoomCount() {
        PriorityQueue<Integer> resultQueue = new PriorityQueue<>();
        resultQueue.offer(resultArr[0][1]);

        for(int i = 1; i < resultArr.length; i++) {
            int startTime = resultArr[i][0];
            int endTime = resultArr[i][1];
            int classEndTime = resultQueue.peek();

            if(classEndTime <= startTime) {
                resultQueue.poll();
            }

            resultQueue.offer(endTime);
        }

        return resultQueue.size();
    }
}
