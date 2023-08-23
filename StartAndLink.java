import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StartAndLink {
    public static int[][] resultArr;
    public static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int person = Integer.parseInt(br.readLine());
        initResultArr(person);

        for(int i = 0; i < person; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        boolean[] visit = new boolean[person];

        getPermutation(visit, 0, 0, person/2);

        System.out.println(minValue);
    }

    public static void getPermutation(boolean[] visit, int current, int start, int target) {
        if(current == target) {
            getMinValue(visit);
            return;
        }

        for(int i = start; i < visit.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                getPermutation(visit, current + 1, i + 1, target);
                visit[i] = false;
            }
        }
    }

    public static void getMinValue(boolean[] visit) {
        int startScore = getStartTeamScore(visit);
        int linkScore = getLinkTeamScore(visit);

        minValue = Math.min(minValue, Math.abs(startScore - linkScore));
    }

    public static int getStartTeamScore(boolean[] visit) {
        int score = 0;

        for(int i = 0; i < visit.length; i++) {
            for(int j = i+1; j < visit.length; j++) {
                if(!visit[i] && !visit[j]) {
                    score += (resultArr[i][j] + resultArr[j][i]);
                }
            }
        }

        return score;
    }

    public static int getLinkTeamScore(boolean[] visit) {
        int score = 0;

        for(int i = 0; i < visit.length; i++) {
            for(int j = i+1; j < visit.length; j++) {
                if(visit[i] && visit[j]) {
                    score += (resultArr[i][j] + resultArr[j][i]);
                }
            }
        }

        return score;
    }

    public static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    public static void initResultArr(int person) {
        resultArr = new int[person][person];
    }
}
