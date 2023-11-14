import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Jump {
    public static String[][] resultArr = new String[5][5];
    public static Set<String> resultSet = new HashSet<>();
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 5; i++) {
            resultArr[i] = br.readLine().split(" ");;
        }

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                dfs(i, j, resultArr[i][j], 0);
            }
        }

        System.out.println(resultSet.size());
    }

    public static void dfs(int height, int width, String value, int length) {
        if(length == 5) {
            resultSet.add(value);
            return;
        }

        for(int i = 0; i < 4; i++) {
            int x = width + dx[i];
            int y = height + dy[i];

            if(x < 0 || y < 0 || x >= 5 || y >= 5) continue;

            dfs(y, x, value + resultArr[y][x], length + 1);
        }
    }
}
