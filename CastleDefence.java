import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CastleDefence {
    private static int[][] resultArr;
    private static int[] bowMan1;
    private static int[] bowMan2;
    private static int[] bowMan3;
    private static int maxResult = Integer.MIN_VALUE;
    private static int ATTACK_RANGE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // height
        int M = Integer.parseInt(st.nextToken()); // width
        ATTACK_RANGE = Integer.parseInt(st.nextToken()); // attack range

        initResultArr(N, M);

        // 0 -> empty, 1 -> enemy
        for(int height = 0; height < N; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, height);
        }

        boolean[] visitArr = new boolean[M];

        getPermutation(visitArr, 0, 3);

        System.out.println(maxResult);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int width = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][width++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void getPermutation(boolean[] visitArr, int current, int target) {
        if(current == target) {
            setBowMan(visitArr);
            int[][] copyArr = copyArr();

            int gameResult = startGame(copyArr);

            maxResult = Math.max(maxResult, gameResult);

            return;
        }

        for(int index = 0; index < visitArr.length; index++) {
            if(!visitArr[index]) {
                visitArr[index] = true;
                getPermutation(visitArr, current + 1, target);
                visitArr[index] = false;
            }
        }
    }

    private static void setBowMan(boolean[] visitArr) {
        boolean flag1 = false;
        boolean flag2 = false;

        for(int index = 0; index < visitArr.length; index++) {
            if(!visitArr[index]) continue;

            if(!flag1) {
                bowMan1 = new int[]{resultArr.length, index};
                flag1 = true;
                continue;
            }

            if(!flag2) {
                bowMan2 = new int[]{resultArr.length, index};
                flag2 = true;
                continue;
            }

            bowMan3 = new int[]{resultArr.length, index};
        }
    }

    private static int[][] copyArr() {
        int[][] arr = new int[resultArr.length][resultArr[0].length];

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr[0].length; width++) {
                arr[height][width] = resultArr[height][width];
            }
        }

        return arr;
    }

    private static int startGame(int[][] arr) {
        int gameScore = 0;

        while(true) {
            int[] bowMan1Target = getBowMan1Target(arr);
            int[] bowMan2Target = getBowMan2Target(arr);
            int[] bowMan3Target = getBowMan3Target(arr);

            int height = bowMan1Target[0];
            int width = bowMan1Target[1];

            if(height != -1 && width != -1) {
                int value = arr[height][width];

                if(value != 0) {
                    gameScore++;
                    arr[height][width] = 0;
                }
            }

            height = bowMan2Target[0];
            width = bowMan2Target[1];

            if(height != -1 && width != -1) {
                int value = arr[height][width];

                if(value != 0) {
                    gameScore++;
                    arr[height][width] = 0;
                }
            }

            height = bowMan3Target[0];
            width = bowMan3Target[1];

            if(height != -1 && width != -1) {
                int value = arr[height][width];

                if(value != 0) {
                    gameScore++;
                    arr[height][width] = 0;
                }
            }

            nextStage(arr);

            if(checkGameCondition(arr)) break;
        }

        return gameScore;
    }

    private static int[] getBowMan1Target(int[][] arr) {
        int[] target = new int[2];
        int minX = Integer.MAX_VALUE;
        int minDistance = Integer.MAX_VALUE;
        boolean flag = false;

        for(int height = 0; height < arr.length; height++) {
            for(int width = 0; width < arr[0].length; width++) {
                if(arr[height][width] == 0) continue;

                int distance = getDistance(width, height, bowMan1[1], bowMan1[0]);

                if(distance > ATTACK_RANGE) continue;
                if(minDistance < distance) continue;
                if(minDistance == distance && width < minX) {
                    minX = width;
                    target[0] = height;
                    target[1] = width;
                    flag = true;
                    continue;
                }
                if(distance < minDistance) {
                    minX = width;
                    minDistance = distance;

                    target[0] = height;
                    target[1] = width;
                    flag = true;
                }
            }
        }

        if(flag) return target;
        return new int[]{-1, -1};
    }

    private static int[] getBowMan2Target(int[][] arr) {
        int[] target = new int[2];
        int minX = Integer.MAX_VALUE;
        int minDistance = Integer.MAX_VALUE;
        boolean flag = false;

        for(int height = 0; height < arr.length; height++) {
            for(int width = 0; width < arr[0].length; width++) {
                if(arr[height][width] == 0) continue;

                int distance = getDistance(width, height, bowMan2[1], bowMan2[0]);

                if(distance > ATTACK_RANGE) continue;
                if(minDistance < distance) continue;
                if(minDistance == distance && width < minX) {
                    minX = width;
                    target[0] = height;
                    target[1] = width;
                    flag = true;
                    continue;
                }
                if(distance < minDistance) {
                    minX = width;
                    minDistance = distance;

                    target[0] = height;
                    target[1] = width;
                    flag = true;
                }
            }
        }

        if(flag) return target;
        return new int[]{-1, -1};
    }

    private static int[] getBowMan3Target(int[][] arr) {
        int[] target = new int[2];
        int minX = Integer.MAX_VALUE;
        int minDistance = Integer.MAX_VALUE;
        boolean flag = false;

        for(int height = 0; height < arr.length; height++) {
            for(int width = 0; width < arr[0].length; width++) {
                if(arr[height][width] == 0) continue;

                int distance = getDistance(width, height, bowMan3[1], bowMan3[0]);

                if(distance > ATTACK_RANGE) continue;
                if(minDistance < distance) continue;
                if(minDistance == distance && width < minX) {
                    minX = width;
                    target[0] = height;
                    target[1] = width;
                    flag = true;
                    continue;
                }
                if(distance < minDistance) {
                    minX = width;
                    minDistance = distance;

                    target[0] = height;
                    target[1] = width;
                    flag = true;
                }
            }
        }

        if(flag) return target;
        return new int[]{-1, -1};
    }

    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }

    private static void nextStage(int[][] arr) {
        for(int height = arr.length-2; height >= 0; height--) {
            for(int width = 0; width < arr[0].length; width++) {
                arr[height+1][width] = arr[height][width];
            }
        }

        Arrays.fill(arr[0], 0);
    }

    private static boolean checkGameCondition(int[][] arr) {
        int sum = 0;

        for(int height = 0; height < arr.length; height++) {
            for(int width = 0; width < arr[0].length; width++) {
                sum += arr[height][width];
            }
        }

        return sum == 0;
    }
}

/*

4 4 4
1 1 1 0
1 1 1 1
0 0 1 0
0 0 1 1

 */
