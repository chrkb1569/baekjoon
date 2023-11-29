import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PreviousPermutation {
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] infoArr = br.readLine().split(" ");

        initResultArr(N);
        mkResultArr(infoArr);

        if(checkValidation()) {
            for(int value : resultArr) {
                System.out.print(value + " ");
            }
            return;
        }
        System.out.println("-1");
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void mkResultArr(String[] infoArr) {
        for(int i = 0; i < resultArr.length; i++) {
            resultArr[i] = Integer.parseInt(infoArr[i]);
        }
    }

    private static boolean checkValidation() {
        int index = resultArr.length - 1;

        while(index > 0 && resultArr[index - 1] <= resultArr[index]) {
            index--;
        }

        if(index <= 0) return false;

        int otherIndex = resultArr.length - 1;

        while(otherIndex > 0 && resultArr[index - 1] <= resultArr[otherIndex]) {
            otherIndex--;
        }

        int box = resultArr[index - 1];
        resultArr[index - 1] = resultArr[otherIndex];
        resultArr[otherIndex] = box;

        otherIndex = resultArr.length - 1;

        while(index < otherIndex) {
            box = resultArr[index];
            resultArr[index] = resultArr[otherIndex];
            resultArr[otherIndex] = box;

            index++;
            otherIndex--;
        }

        return true;
    }
}
