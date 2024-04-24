import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Number_Of_Nth {
    private static PriorityQueue<Integer> resultQueue = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for(int height = 0; height < N; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultQueue(st);
        }

        System.out.println(getNthNumber(N));
    }


    private static void mkResultQueue(StringTokenizer st) {
        for(;st.hasMoreTokens();) {
            resultQueue.add(Integer.parseInt(st.nextToken()));
        }
    }

    private static int getNthNumber(int N) {
        int number = 0;
        int index = 1;

        while(!resultQueue.isEmpty()) {
            number = resultQueue.poll();

            if(index == N) break;
            index++;
        }

        return number;
    }
}
