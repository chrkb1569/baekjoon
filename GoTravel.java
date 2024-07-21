import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class GoTravel {
    private static List<List<Integer>> resultList = new ArrayList<>();
    private static int[] parentArr;
    private static int[] travelPlan;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 도시의 수
        int M = Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시의 수

        initParentArr(N);
        initTravelPlan(M);
        initResultList(N);

        for(int link = 0; link < N; link++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultList(link, st);
        }

        st = new StringTokenizer(br.readLine(), " ");
        mkTravelPlan(st);

        printResult();
    }

    private static void initParentArr(int length) {
        parentArr = new int[length];

        for(int index = 0; index < parentArr.length; index++) {
            parentArr[index] = index;
        }
    }

    private static void initTravelPlan(int length) {
        travelPlan = new int[length];
    }

    private static void initResultList(int length) {
        resultList = new ArrayList<>(length);

        for(int index = 0; index < length; index++) {
            resultList.add(new ArrayList<>());
        }
    }

    private static void mkResultList(int index, StringTokenizer st) {
        int linkNumber = 0;

        for(;st.hasMoreTokens();) {
            int linkValue = Integer.parseInt(st.nextToken());

            if(linkValue == 1) {
                resultList.get(index).add(linkNumber);

                int indexParent = getParent(index);
                int linkParent = getParent(linkNumber);

                if(indexParent != linkParent) {
                    unionGroup(index, linkNumber);
                }
            }

            linkNumber++;
        }

        for(int arrIndex = 0; arrIndex < resultList.size(); arrIndex++) {
            int parentNumber = getParent(arrIndex);

            for(int value : resultList.get(arrIndex)) {
                int valueParent = getParent(value);

                if(parentNumber != valueParent) {
                    unionGroup(arrIndex, value);
                }
            }
        }
    }

    private static void mkTravelPlan(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            travelPlan[index++] = Integer.parseInt(st.nextToken()) - 1;
        }
    }

    private static int getParent(int index) {
        int parent = parentArr[index];

        if(index == parent) return parent;

        parentArr[index] = getParent(parent);

        return parentArr[index];
    }

    private static void unionGroup(int indexA, int indexB) {
        int parentA = getParent(indexA);
        int parentB = getParent(indexB);

        if(parentA < parentB) {
            parentArr[indexB] = parentA;
            return;
        }

        parentArr[indexA] = parentB;
    }

    private static void printResult() {
        boolean flag = true;
        int groupNumber = getParent(parentArr[travelPlan[0]]);

        for(int index = 1; index < travelPlan.length; index++) {
            int number = parentArr[travelPlan[index]];

            if(groupNumber == number) continue;

            int parentNumber = getParent(number);

            if(parentNumber != groupNumber) {
                flag = false;
                break;
            }
        }

        if(flag) {
            System.out.println("YES");
            return;
        }

        System.out.println("NO");
    }
}