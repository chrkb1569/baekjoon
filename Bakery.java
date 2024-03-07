import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bakery {
    private static char[][] resultArr;
    private static final char BUILDING_SPACE = 'x';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int R = Integer.parseInt(st.nextToken()); // height
        int C = Integer.parseInt(st.nextToken()); // width

        initResultArr(R, C);

        for(int height = 0; height < R; height++) {
            char[] infoArr = br.readLine().toCharArray();
            mkResultArr(infoArr, height);
        }

        int count = 0;

        for(int height = 0; height < resultArr.length; height++) {
            if(dfs(height, 0)) count++;
        }

        System.out.println(count);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new char[height][width];
    }

    private static void mkResultArr(char[] infoArr, int height) {
        for(int index = 0; index < infoArr.length; index++) {
            resultArr[height][index] = infoArr[index];
        }
    }
    
    private static boolean dfs(int height, int width) {
        resultArr[height][width] = BUILDING_SPACE;

        if(width == resultArr[0].length-1) return true;

        if(height - 1 >= 0 && resultArr[height - 1][width + 1] != BUILDING_SPACE) {
            if(dfs(height - 1, width + 1)) return true;
        }

        if(resultArr[height][width + 1] != BUILDING_SPACE) {
            if(dfs(height, width + 1)) return true;
        }

        if(height + 1 < resultArr.length && resultArr[height + 1][width + 1] != BUILDING_SPACE) {
            if(dfs(height + 1, width + 1)) return true;
        }

        return false;
    }
}