import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Truck {

    private static int[] truckArr;

    private final static ArrayDeque<Integer> resultDeque = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken()); // 다리를 건너는 트럭의 수 (1 ≤ n ≤ 1,000)
        int w = Integer.parseInt(st.nextToken()); // 다리의 길이 (1 ≤ w ≤ 100)
        int L = Integer.parseInt(st.nextToken()); // 다리의 최대 하중 (10 ≤ L ≤ 1,000)

        st = new StringTokenizer(br.readLine(), " ");

        initTruckArr(n);
        mkTruckArr(st);

        int time = moveTruck(w, L);

        System.out.println(time);
    }

    private static void initTruckArr(int length) {
        truckArr = new int[length];
    }

    private static void mkTruckArr(StringTokenizer st) {
        for(int index = 0; index < truckArr.length; index++) {
            truckArr[index] = Integer.parseInt(st.nextToken());
        }
    }

    private static int moveTruck(int distance, int limit) {
        int time = 1;
        int truckIndex = 1;
        int sum = truckArr[0];
        resultDeque.addLast(sum);

        while(true) {
            if(sum == 0 && truckIndex == truckArr.length) break;

            time++;

            int length = resultDeque.size();

            // 도착하는 트럭이 존재하지 않는 경우
            if(length < distance) {
                if(truckIndex >= truckArr.length) {
                    resultDeque.addLast(0);
                    continue;
                }

                int currentTruck = truckArr[truckIndex];

                if(sum + currentTruck <= limit) {
                    sum += currentTruck;
                    truckIndex++;
                    resultDeque.addLast(currentTruck);
                    continue;
                }

                resultDeque.addLast(0);
                continue;
            }

            // 도착하는 트럭이 존재하는 경우
            int arrivedTruck = resultDeque.removeFirst();
            sum -= arrivedTruck;

            // 추가할 트럭이 존재하지 않는 경우
            if(truckIndex >= truckArr.length) {
                resultDeque.addLast(0);
                continue;
            }

            // 추가할 트럭이 존재하는 경우
            int currentTruck = truckArr[truckIndex];

            if(sum + currentTruck <= limit) {
                sum += currentTruck;
                truckIndex++;
                resultDeque.addLast(currentTruck);
                continue;
            }

            resultDeque.addLast(0);
        }

        return time;
    }
}
