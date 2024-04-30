import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SelectNumber {
    private static int[] resultArr;
    private static List<Integer> resultList = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);

        for(int number = 1; number <= N; number++) {
            resultArr[number] = Integer.parseInt(br.readLine());
        }

        boolean[] visitArr = new boolean[N + 1];

        for(int index = 1; index < visitArr.length; index++) {
            visitArr[index] = true;
            dfs(visitArr, index, index);
            visitArr[index] = false;
        }

        printResult();
    }

    private static void initResultArr(int length) {
        resultArr = new int[length + 1];
    }

    private static void dfs(boolean[] visitArr, int currentNumber, int target) {
        if(!visitArr[resultArr[currentNumber]]) {
            visitArr[resultArr[currentNumber]] = true;
            dfs(visitArr, resultArr[currentNumber], target);
            visitArr[resultArr[currentNumber]] = false;
        }

        if(resultArr[currentNumber] == target) resultList.add(currentNumber);
    }

    private static void printResult() {
        StringBuffer sb = new StringBuffer();
        Collections.sort(resultList);

        sb.append(resultList.size()).append("\n");

        for(int value : resultList) {
            sb.append(value).append("\n");
        }

        System.out.println(sb);
    }
}
