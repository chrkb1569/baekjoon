import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class NumberOfLink {
    private static int[] parentArr;
    private static List<Integer>[] linkArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int node = Integer.parseInt(st.nextToken());
        int link = Integer.parseInt(st.nextToken());

        initResultArr(node);
        initLinkArr(node);

        for(int i = 0; i < link; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st);
        }

        mkParentArr();

        System.out.println(getUnionCount());
    }

    private static void initResultArr(int length) {
        parentArr = new int[length + 1];

        for(int i = 0; i < parentArr.length; i++) {
            parentArr[i] = i;
        }
    }

    private static void initLinkArr(int length) {
        linkArr = new List[length + 1];

        for(int i = 0; i < linkArr.length; i++) {
            linkArr[i] = new LinkedList<>();
        }
    }

    private static void mkResultArr(StringTokenizer st) {
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        linkArr[from].add(to);
        linkArr[to].add(from);
    }

    private static void mkParentArr() {
        for(int i = 1; i < linkArr.length; i++) {
            for(int member : linkArr[i]) {
                int parentA = getParent(i);
                int parentB = getParent(member);

                if(parentA != parentB) {
                    union(i, member);
                }
            }
        }
    }

    private static int getParent(int index) {
        if(parentArr[index] == index) return index;
        parentArr[index] = getParent(parentArr[index]);

        return parentArr[index];
    }

    private static void union(int a, int b) {
        int parentA = getParent(a);

        parentArr[b] = parentA;
    }

    private static int getUnionCount() {
        boolean[] visitArr = new boolean[parentArr.length];
        int count = 0;

        for(int i = 1; i < parentArr.length; i++) {
            int parentNumber = parentArr[i];

            if(visitArr[parentNumber]) continue;
            visitArr[parentNumber] = true;
            count++;
        }

        return count;
    }
}
