import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Taste_Wine {

    public static int[] wineArr; // 맨 처음 포도주의 값을 저장하기 위한 배열 wineArr 선언
    public static int[] resultArr; // 포도주를 가장 많이 마실 수 있는 결과를 구하기 위한 배열 resultArr 선언

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int wine = Integer.parseInt(br.readLine());

        initWineArr(wine); // 사용자로부터 입력 받은 값을 저장하기 위하여 배열 초기화
        initResultArr(wine); // 결과값을 구하기 위하여 배열 초기화

        for(int i = 1; i <= wine; i++) {
            wineArr[i] = Integer.parseInt(br.readLine()); // 사용자롤부터 입력 받은 포도주의 값들을 배열에 저장
        }

        mkResultArr();

        System.out.println(resultArr[wine]);
    }

    // 사용자로부터 입력 받은 배열을 통하여 가장 많이 마실 수 있는 포도주의 양 구함
    public static void mkResultArr() {
        resultArr[1] = wineArr[1];

        if(resultArr.length == 2) return;

        resultArr[2] = wineArr[1] + wineArr[2];

        if(resultArr.length == 3) return;

        for(int i = 3; i < resultArr.length; i++) {
            resultArr[i] = Math.max(resultArr[i-1],
                    Math.max(resultArr[i-2] + wineArr[i], resultArr[i-3] + wineArr[i-1] + wineArr[i])
            );
        }
    }

    public static void initWineArr(int value) {
        wineArr = new int[value + 1];
    }

    public static void initResultArr(int value) {
        resultArr = new int[value + 1];
    }
}