import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Snake {
    private static int[][] resultArr;
    private static List<int[]> previousMove = new LinkedList<>(); // 뱀이 이동한 경로를 저장
    private static Map<Integer, String> resultMap = new HashMap<>();
    private static int[] dx = {1, 0, -1, 0}; // 동 남 서 북
    private static int[] dy = {0, 1, 0, -1}; // 동 남 서 북
    private static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int apple = Integer.parseInt(br.readLine());

        initResultArr(N);

        for(int i = 0; i < apple; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st);
        }

        int move = Integer.parseInt(br.readLine());

        for(int i = 0; i < move; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultMap(st);
        }

        playGame();

        System.out.println(count);
    }

    private static void initResultArr(int N) {
        resultArr = new int[N+1][N+1];
    }

    private static void mkResultArr(StringTokenizer st) {
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        resultArr[height][width] = 1;
    }

    private static void mkResultMap(StringTokenizer st) {
        int value = Integer.parseInt(st.nextToken());
        String command = st.nextToken();

        resultMap.put(value, command);
    }

    private static void playGame() {
        int direction = 0;
        int x = 1;
        int y = 1;
        int length = 1;

        previousMove.add(new int[]{y, x});

        while(true) {
            x = x + dx[direction];
            y = y + dy[direction];
            count++;

            if(!checkValidation(y, x)) break;

            previousMove.add(new int[]{y, x});

            if(resultArr[y][x] == 1) {
                length++;
                resultArr[y][x] = 0;
            }

            trimList(length);

            if(resultMap.containsKey(count) && resultMap.get(count).equals("D")) {
                direction = (direction + 1) % 4;
            }

            if(resultMap.containsKey(count) && resultMap.get(count).equals("L")) {
                direction = (direction + 3) % 4;
            }
        }
    }

    private static boolean checkValidation(int height, int width) {
        if(height <= 0 || width <= 0 || height >= resultArr.length || width >= resultArr[0].length) return false;

        for(int[] arr : previousMove) {
            int preHeight = arr[0];
            int preWidth = arr[1];

            if(preHeight == height && preWidth == width) return false;
        }

        return true;
    }

    private static void trimList(int length) {
        if(previousMove.size() <= length) return;

        int size = previousMove.size();

        for(int i = 0; i < size - length; i++) {
            previousMove.remove(0);
        }
    }
}
