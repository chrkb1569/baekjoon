import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TermProject {
    private static int[] resultArr; // 초기 사이클 값을 저장하기 위한 배열
    private static boolean[] visitArr; // DFS에서 방문 여부를 저장하기 위한 배열
    private static boolean[] processArr; // DFS에서 작업 여부를 저장하기 위한 배열
    private static int personCount; // 팀을 이룬 학생의 수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // testCase

        for(int testCase = 1; testCase <= T; testCase++) {
            int n = Integer.parseInt(br.readLine()); // 학생들의 수
            st = new StringTokenizer(br.readLine(), " ");

            personCount = 0;

            initResultArr(n);
            initVisitArr(n);
            initProcessArr(n);

            mkResultArr(st);

            for(int index = 1; index <= n; index++) {
                if(processArr[index]) continue;

                getOutSider(index);
            }

            sb.append(n - personCount).append("\n");
        }

        System.out.println(sb);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length + 1];
    }

    private static void initVisitArr(int length) {
        visitArr = new boolean[length + 1];
    }

    private static void initProcessArr(int length) {
        processArr = new boolean[length + 1];
    }

    private static void mkResultArr(StringTokenizer st) {
        int width = 1;

        for(;st.hasMoreTokens();) {
            resultArr[width++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void getOutSider(int index) {
        if(processArr[index]) return;
        if(visitArr[index]) {
            processArr[index] = true;
            personCount++;
        }

        visitArr[index] = true;

        getOutSider(resultArr[index]);

        visitArr[index] = false;
        processArr[index] = true;
    }
}
