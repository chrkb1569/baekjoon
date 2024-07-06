import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Game_2048 {
    private static int[][] resultArr;
    private static final int MAX_VALUE = 1024 * 400;
    private static int MAX_BLOCK = 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);

        for(int height = 0; height < N; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, height);
        }

        getMaxBlock(resultArr, 0);

        System.out.println(MAX_BLOCK);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length][length];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int width = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][width++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void getMaxBlock(int[][] arr, int depth) {
        if(MAX_BLOCK == MAX_VALUE) return;
        if(depth > 5) return;

        for(int height = 0; height < arr.length; height++) {
            for(int width = 0; width < arr[height].length; width++) {
                MAX_BLOCK = Math.max(arr[height][width], MAX_BLOCK);
            }
        }

        getMaxBlock(moveUp(arr), depth + 1);
        getMaxBlock(moveRight(arr), depth + 1);
        getMaxBlock(moveDown(arr), depth + 1);
        getMaxBlock(moveLeft(arr), depth + 1);
    }

    private static int[][] moveUp(int[][] arr) {
        int[][] movedArr = copyArr(arr);

        trimUp(movedArr);

        for(int width = 0; width < movedArr.length; width++) {
            for(int height = 1; height < movedArr.length; height++) {
                if(movedArr[height][width] == movedArr[height - 1][width]) {
                    movedArr[height-1][width] *= 2;
                    movedArr[height][width] = 0;
                }
            }
        }

        trimUp(movedArr);

        return movedArr;
    }

    private static int[][] moveRight(int[][] arr) {
        int[][] movedArr = copyArr(arr);

        trimRight(movedArr);

        for(int height = 0; height < movedArr.length; height++) {
            for(int width = movedArr.length - 2; width >= 0; width--) {
                if(movedArr[height][width] == movedArr[height][width + 1]) {
                    movedArr[height][width + 1] *= 2;
                    movedArr[height][width] = 0;
                }
            }
        }

        trimRight(movedArr);

        return movedArr;
    }

    private static int[][] moveDown(int[][] arr) {
        int[][] movedArr = copyArr(arr);

        trimDown(movedArr);

        for(int width = 0; width < movedArr.length; width++) {
            for(int height = movedArr.length - 2; height >= 0; height--) {
                if(movedArr[height][width] == movedArr[height + 1][width]) {
                    movedArr[height + 1][width] *= 2;
                    movedArr[height][width] = 0;
                }
            }
        }

        trimDown(movedArr);

        return movedArr;
    }

    private static int[][] moveLeft(int[][] arr) {
        int[][] movedArr = copyArr(arr);

        trimLeft(movedArr);

        for(int height = 0; height < movedArr.length; height++) {
            for(int width = 1; width < movedArr.length; width++) {
                if(movedArr[height][width] == movedArr[height][width-1]) {
                    movedArr[height][width-1] *= 2;
                    movedArr[height][width] = 0;
                }
            }
        }

        trimLeft(movedArr);

        return movedArr;
    }

    private static int[][] copyArr(int[][] origin) {
        int[][] cpyArr = new int[origin.length][origin.length];

        for(int height = 0; height < origin.length; height++) {
            for(int width = 0; width < origin.length; width++) {
                cpyArr[height][width] = origin[height][width];
            }
        }

        return cpyArr;
    }

    private static void trimUp(int[][] arr) {
        for(int width = 0; width < arr.length; width++) {
            int index = 0;

            for(int height = 0; height < arr.length; height++) {
                if(arr[height][width] == 0) continue;

                while(index < height) {
                    int value = arr[index][width];

                    if(value == 0) {
                        arr[index][width] = arr[height][width];
                        arr[height][width] = 0;
                        index++;
                        break;
                    }

                    index++;
                }
            }
        }
    }

    private static void trimRight(int[][] arr) {
        for(int height = 0; height < arr.length; height++) {
            int index = arr.length - 1;

            for(int width = arr.length - 1; width >= 0; width--) {
                if(arr[height][width] == 0) continue;

                while(index > width) {
                    int value = arr[height][index];

                    if(value == 0) {
                        arr[height][index] = arr[height][width];
                        arr[height][width] = 0;
                        index--;
                        break;
                    }

                    index--;
                }
            }
        }
    }

    private static void trimDown(int[][] arr) {
        for(int width = 0; width < arr.length; width++) {
            int index = arr.length - 1;

            for(int height = arr.length - 1; height >= 0; height--) {
                if(arr[height][width] == 0) continue;

                while(index > height) {
                    int value = arr[index][width];

                    if(value == 0) {
                        arr[index][width] = arr[height][width];
                        arr[height][width] = 0;
                        index--;
                        break;
                    }

                    index--;
                }
            }
        }
    }

    private static void trimLeft(int[][] arr) {
        for(int height = 0; height < arr.length; height++) {
            int index = 0;

            for(int width = 0; width < arr.length; width++) {
                if(arr[height][width] == 0) continue;

                while(index < width) {
                    int value = arr[height][index];

                    if(value == 0) {
                        arr[height][index] = arr[height][width];
                        arr[height][width] = 0;
                        index++;
                        break;
                    }

                    index++;
                }
            }
        }
    }
}
