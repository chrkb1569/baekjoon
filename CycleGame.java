import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CycleGame {
    private static int[] parentArr;
    private static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int joinPoint = 0;

        int point = Integer.parseInt(st.nextToken());
        int link = Integer.parseInt(st.nextToken());

        initParentArr(point);

        for(int line = 0; line < link; line++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int parentOfFrom = findParent(from);
            int parentOfTo = findParent(to);

            if(parentOfFrom == parentOfTo) {
                flag = true;
                joinPoint = line + 1;
                break;
            }
            union(from, to);
        }

        printResult(joinPoint);
    }

    private static void initParentArr(int length) {
        parentArr = new int[length];

        for(int i = 0; i < length; i++) {
            parentArr[i] = i;
        }
    }

    private static int findParent(int index) {
        if(parentArr[index] == index) return index;
        parentArr[index] = findParent(parentArr[index]);
        return parentArr[index];
    }

    private static void union(int indexA, int indexB) {
        int parentA = findParent(indexA);
        int parentB = findParent(indexB);

        if(parentA < parentB) {
            parentArr[parentB] = parentA;
            return;
        }

        parentArr[parentA] = parentB;
    }

    private static void printResult(int joinPoint) {
        if(flag) {
            System.out.println(joinPoint);
            return;
        }
        System.out.println(0);
    }
}
