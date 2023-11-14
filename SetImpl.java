import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SetImpl {
    private static boolean[] resultArr = new boolean[21];
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            if(st.countTokens() == 2) {
                String command = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                processCommand(command, value);

                continue;
            }

            processCommand(st.nextToken());
        }

        System.out.println(sb);
    }

    public static void processCommand(String command, int value) {
        if(command.equals("add")) {
            add(value);
            return;
        }

        if(command.equals("remove")) {
            remove(value);
            return;
        }

        if(command.equals("check")) {
            check(value);
            return;
        }

        toggle(value);
    }

    public static void processCommand(String command) {
        if(command.equals("all")) {
            all();
            return;
        }
        empty();
    }

    public static void add(int x) {
        if(resultArr[x]) return;
        resultArr[x] = true;
    }

    public static void remove(int x) {
        if(!resultArr[x]) return;
        resultArr[x] = false;
    }

    public static void check(int x) {
        if(resultArr[x]) {
            sb.append("1").append("\n");
            return;
        }
        sb.append("0").append("\n");
    }

    public static void toggle(int x) {
        resultArr[x] = !resultArr[x];
    }

    public static void all() {
        for(int i = 1; i <= 20; i++) {
            resultArr[i] = true;
        }
    }

    public static void empty() {
        for(int i = 1; i <= 20; i++) {
            resultArr[i] = false;
        }
    }
}
