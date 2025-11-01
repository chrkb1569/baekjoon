import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class PopBalloon {

    private static ArrayDeque<int[]> resultDeque = new ArrayDeque<>();

    private final static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        mkResultDeque(st, N);

        startGame();

        System.out.println(sb);
    }

    private static void mkResultDeque(StringTokenizer st, int length) {
        for(int index = 0; index < length; index++) {
            int value = Integer.parseInt(st.nextToken());
            resultDeque.add(new int[]{index + 1, value});
        }
    }

    private static void startGame() {
        while(!resultDeque.isEmpty()) {
            int[] currentBalloon = resultDeque.removeFirst();

            int index = currentBalloon[0];
            int distance = currentBalloon[1];

            sb.append(index).append(" ");

            if(resultDeque.isEmpty()) break;

            move(distance);
        }
    }

    private static void move(int distance) {
        if(distance < 0) {
            moveLeft(Math.abs(distance));
            return;
        }

        moveRight(distance);
    }

    private static void moveLeft(int distance) {
        for(int move = 0; move < distance; move++) {
            resultDeque.addFirst(resultDeque.removeLast());
        }
    }

    private static void moveRight(int distance) {
        for(int move = 0; move < distance - 1; move++) {
            resultDeque.addLast(resultDeque.removeFirst());
        }
    }
}