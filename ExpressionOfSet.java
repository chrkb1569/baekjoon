import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ExpressionOfSet {
    private static int[] parentArr;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        initParentArr(N);

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int signal = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            processSignal(signal, from, to);
        }

        System.out.println(sb);
    }

    private static void initParentArr(int length) {
        parentArr = new int[length + 1];

        for(int i = 0; i < parentArr.length; i++) {
            parentArr[i] = i;
        }
    }

    private static void processSignal(int signal, int from, int to) {
        if(signal == 0) {
            union(from, to);
            return;
        }

        String result = checkUnion(from, to);
        sb.append(result).append("\n");
    }

    private static String checkUnion(int from, int to) {
        if(from == to) return "YES";
        if(checkValidation(from, to)) return "YES";
        return "NO";
    }

    private static boolean checkValidation(int from, int to) {
        return findParent(from) == findParent(to);
    }

    private static int findParent(int index) {
        if(parentArr[index] == index) return parentArr[index];
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
}
