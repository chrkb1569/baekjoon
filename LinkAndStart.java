import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LinkAndStart {
    private static int[][] resultArr;
    private static int minGap = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        for(int i = 1; i <= N/2; i++) {
            boolean[] visitArr = new boolean[N];
            getPermutation(visitArr, 0, 0, i);
        }

        System.out.println(minGap);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length][length];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void getPermutation(boolean[] visit, int start, int current, int target) {
        if(current == target) {
            int gap = findGap(visit);
            minGap = Math.min(minGap, gap);

            return;
        }

        for(int i = start; i < visit.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                getPermutation(visit, i + 1, current + 1, target);
                visit[i] = false;
            }
        }
    }

    private static int findGap(boolean[] visit) {
        int trueTeamScore = 0;
        int falseTeamScore = 0;

        for(int i = 0; i < visit.length; i++) {
            if(!visit[i]) continue;

            for(int j = 0; j < visit.length; j++) {
                if(!visit[j]) continue;

                trueTeamScore += resultArr[i][j];
            }
        }

        for(int i = 0; i < visit.length; i++) {
            if(visit[i]) continue;

            for(int j = 0; j < visit.length; j++) {
                if(visit[j]) continue;
                falseTeamScore += resultArr[i][j];
            }
        }

        return Math.abs(trueTeamScore - falseTeamScore);
    }
}
