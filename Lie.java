import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Lie {
    private static int[] resultArr;
    private static boolean[] knowArr;
    private static boolean[] visitArr;
    private static Set<Integer>[] partySet;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int result = 0;

        int people = Integer.parseInt(st.nextToken());
        int party = Integer.parseInt(st.nextToken());

        initResultArr(people);
        initKnowArr(people);
        initVisitArr(people);
        initPartySet(party);

        st = new StringTokenizer(br.readLine(), " ");
        mkKnowArr(st);

        for(int i = 1; i <= party; i++) {
            String[] infoArr = br.readLine().split(" ");
            int participant = Integer.parseInt(infoArr[0]);

            if(participant == 1) {
                partySet[i].add(Integer.parseInt(infoArr[1]));
                continue;
            }

            for(int j = 1; j < infoArr.length - 1; j++) {
                int a = Integer.parseInt(infoArr[j]);
                int b = Integer.parseInt(infoArr[j+1]);

                if(findParent(a) != findParent(b)) {
                    union(a, b);
                }

                partySet[i].add(a);
                partySet[i].add(b);
            }
        }

        for (int i = 1; i <= people; i++) {
            if(knowArr[i] && !visitArr[i]) {
                int root = findParent(i);

                for (int j = 1; j <= people; j++) {
                    if (findParent(j) == root) {
                        knowArr[j] = true;
                        visitArr[j] = true;
                    }
                }
            }
        }

        for (int i = 1; i <= party; i++) {
            boolean flag = false;

            for (int person : partySet[i]) {
                if(knowArr[person]){
                    flag = true;
                    break;
                }
            }
            if(!flag) result++;
        }

        System.out.println(result);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length + 1];

        for(int i = 0; i < resultArr.length; i++) {
            resultArr[i] = i;
        }
    }

    private static void initKnowArr(int length) {
        knowArr = new boolean[length + 1];
    }

    private static void initVisitArr(int length) {
        visitArr = new boolean[length + 1];
    }

    private static void initPartySet(int length) {
        partySet = new Set[length + 1];

        for(int i = 0; i < partySet.length; i++) {
            partySet[i] = new HashSet<>();
        }
    }

    private static void mkKnowArr(StringTokenizer st) {
        int whoKnow = Integer.parseInt(st.nextToken());

        for(int i = 0; i < whoKnow; i++) {
            int personNumber = Integer.parseInt(st.nextToken());
            knowArr[personNumber] = true;
        }
    }

    private static int findParent(int index) {
        if(resultArr[index] == index) return index;
        resultArr[index] = findParent(resultArr[index]);
        return resultArr[index];
    }

    private static void union(int a, int b) {
        int parentOfB = findParent(b);
        resultArr[parentOfB] = a;
    }
}
