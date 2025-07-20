import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class TreeAndQuery {

    private static int[] resultArr; // 결과값을 저장하기 위한 배열

    private static List<List<Integer>> linkList; // 간선의 정보를 저장하기 위한 배열

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 노드의 수 1 <= N <= 10^5
        int R = Integer.parseInt(st.nextToken()); // 루트 번호 1 <= R <= N
        int Q = Integer.parseInt(st.nextToken()); // 쿼리의 수 1 <= Q <= 10^5

        initLinkList(N);
        initResultArr(N);

        for(int link = 0; link < N - 1; link++) {
            st = new StringTokenizer(br.readLine(), " ");

            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            linkList.get(U).add(V);
            linkList.get(V).add(U);
        }

        mkResultArr(R, -1);

        for(int query = 0; query < Q; query++) {
            int rootNumber = Integer.parseInt(br.readLine());

            sb.append(resultArr[rootNumber]).append("\n");
        }

        System.out.println(sb);
    }

    private static void initLinkList(int length) {
        linkList = new ArrayList<>();

        for(int index = 0; index <= length; index++) {
            linkList.add(new LinkedList<>());
        }
    }

    private static void initResultArr(int length) {
        resultArr = new int[length + 1];
    }

    private static void mkResultArr(int currentNumber, int parentNumber) {
        resultArr[currentNumber] = 1;

        for(int nextNode : linkList.get(currentNumber)) {
            if(nextNode == parentNumber) continue;
            mkResultArr(nextNode, currentNumber);
            resultArr[currentNumber] += resultArr[nextNode];
        }
    }
}