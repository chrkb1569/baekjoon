import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaseBallGame {
    private static boolean[] numberArr = new boolean[900];
    private static Queue<Game> games = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Arrays.fill(numberArr, true);

        for(int game = 0; game < N; game++) {
            st = new StringTokenizer(br.readLine(), " ");

            int number = Integer.parseInt(st.nextToken());
            int strikeCount = Integer.parseInt(st.nextToken());
            int ballCount = Integer.parseInt(st.nextToken());

            games.add(new Game(number, strikeCount, ballCount));
        }

        mkNumberArr();

        System.out.println(getResult());
    }

    private static void mkNumberArr() {
        while(!games.isEmpty()) {
            Game game = games.poll();

            for(int index = 0; index < numberArr.length; index++) {
                if(!numberArr[index]) continue;

                int number = index + 100;

                if(!checkNumberValidation(number)) {
                    numberArr[index] = false;
                    continue;
                }

                int gameNumber = game.getNumber();

                int strikeCount = game.getStrikeCount();
                int ballCount = game.getBallCount();

                int sCount = getStrikeCount(number, gameNumber);
                int bCount = getBallCount(number, gameNumber);

                if(strikeCount != sCount || ballCount != bCount) numberArr[index] = false;
            }
        }
    }

    private static boolean checkNumberValidation(int number) {
        int first = number / 100;
        int second = (number % 100) / 10;
        int third = number % 10;

        if(first == 0 || second == 0 || third == 0) return false;
        return (first != second) && (first != third) && (second != third);
    }

    private static int getStrikeCount(int origin, int gameNumber) {
        int strikeCount = 0;

        if(origin / 100 == gameNumber / 100) strikeCount++;
        if((origin % 100) / 10 == (gameNumber % 100) / 10) strikeCount++;
        if(origin % 10 == gameNumber % 10) strikeCount++;

        return strikeCount;
    }

    private static int getBallCount(int origin, int gameNumber) {
        int ballCount = 0;

        int firstNumber = gameNumber / 100;
        int secondNumber = (gameNumber % 100) / 10;
        int thirdNumber = gameNumber % 10;

        int originFirst = origin / 100;
        int originSecond = (origin % 100) / 10;
        int originThird = origin % 10;

        if(firstNumber == originSecond || firstNumber == originThird) ballCount++;
        if(secondNumber == originFirst || secondNumber == originThird) ballCount++;
        if(thirdNumber == originFirst || thirdNumber == originSecond) ballCount++;

        return ballCount;
    }

    private static int getResult() {
        int count = 0;

        for(boolean value : numberArr) {
            if(value) count++;
        }

        return count;
    }

    private static class Game {
        private int number;
        private int strikeCount;
        private int ballCount;

        public Game(int number, int strikeCount, int ballCount) {
            this.number = number;
            this.strikeCount = strikeCount;
            this.ballCount = ballCount;
        }

        public int getNumber() {
            return this.number;
        }

        public int getStrikeCount() {
            return this.strikeCount;
        }

        public int getBallCount() {
            return this.ballCount;
        }
    }
}