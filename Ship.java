import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Ship {
    private static int[] craneArr;
    private static List<Integer> boxList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // crane
        st = new StringTokenizer(br.readLine(), " "); // crane_weight

        initCraneArr(N);
        mkCraneArr(st);

        int M = Integer.parseInt(br.readLine()); // box
        st = new StringTokenizer(br.readLine(), " "); // box_weight

        mkBoxList(st);

        if(craneArr[0] < boxList.get(0)) {
            System.out.println(-1);
            return;
        }

        int time = 0;

        while(!boxList.isEmpty()) {
            int craneIndex = 0;
            int boxIndex = 0;

            while(craneIndex < craneArr.length) {
                if(boxIndex == boxList.size()) break;
                if(craneArr[craneIndex] >= boxList.get(boxIndex)) {
                    boxList.remove(boxIndex);
                    craneIndex++;
                    continue;
                }
                boxIndex++;
            }

            time++;
        }

        System.out.println(time);
    }

    private static void initCraneArr(int length) {
        craneArr = new int[length];
    }

    private static void mkCraneArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            craneArr[index++] = Integer.parseInt(st.nextToken());
        }

        craneArr = Arrays.stream(craneArr).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue).toArray();
    }

    private static void mkBoxList(StringTokenizer st) {
        for(;st.hasMoreTokens();) {
            boxList.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(boxList, Collections.reverseOrder());
    }
}