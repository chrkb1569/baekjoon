import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ComeBackHome {
    private static String[][] resultArr;
    private static boolean[][] visitArr;
    private static int pathCount = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int R = Integer.parseInt(st.nextToken()); // height
        int C = Integer.parseInt(st.nextToken()); // width
        int K = Integer.parseInt(st.nextToken()); // distance

        initResultArr(R, C);
        initVisitArr(R, C);

        for(int height = 0; height < resultArr.length; height++) {
            String[] infoArr = br.readLine().split("");
            mkResultArr(infoArr, height);
        }

        visitArr[R-1][0] = true;
        dfs(R-1, 0, 1, K);

        System.out.println(pathCount);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new String[height][width];
    }

    private static void initVisitArr(int height, int width) {
        visitArr = new boolean[height][width];
    }

    private static void mkResultArr(String[] infoArr, int height) {
        resultArr[height] = infoArr;
    }

    private static void dfs(int height, int width, int distance, int target) {
        if(distance > target) return;
        if(height == 0 && width == resultArr[0].length-1 && distance == target) {
            pathCount++;
            return;
        }

        if(height - 1 >= 0 && !visitArr[height-1][width] && resultArr[height-1][width].equals(".")) {
            visitArr[height-1][width] = true;
            dfs(height-1, width, distance + 1, target);
            visitArr[height-1][width] = false;
        }

        if(width + 1 < resultArr[0].length && !visitArr[height][width + 1] && resultArr[height][width + 1].equals(".")) {
            visitArr[height][width+1] = true;
            dfs(height, width + 1, distance + 1, target);
            visitArr[height][width+1] = false;
        }

        if(height + 1 < resultArr.length && !visitArr[height+1][width] && resultArr[height+1][width].equals(".")) {
            visitArr[height+1][width] = true;
            dfs(height + 1, width, distance + 1, target);
            visitArr[height+1][width] = false;
        }

        if(width - 1 >= 0 && !visitArr[height][width-1] && resultArr[height][width-1].equals(".")) {
            visitArr[height][width-1] = true;
            dfs(height, width - 1, distance + 1, target);
            visitArr[height][width-1] = false;
        }
    }
}
