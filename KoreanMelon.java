import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class KoreanMelon {
    private static String[] commandArr = new String[6];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int melon = Integer.parseInt(br.readLine());

        for(int i = 0; i < 6; i++) {
            commandArr[i] = br.readLine();
        }

        int area = getArea(getType());

        System.out.println(melon * area);
    }

    private static int getArea(int type) {
        if(type == 1) return getAreaOfType1();
        if(type == 2) return getAreaOfType2();
        if(type == 3) return getAreaOfType3();
        return getAreaOfType4();
    }

    private static int getType() {
        Map<String, Integer> commandMap = new HashMap<>();

        for(String value : commandArr) {
            String command = value.split(" ")[0];

            commandMap.put(command, commandMap.getOrDefault(command, 0) + 1);
        }

        int oneCount = commandMap.get("1");
        int twoCount = commandMap.get("2");
        int threeCount = commandMap.get("3");
        int fourCount = commandMap.get("4");

        if(twoCount == 2 && threeCount == 2) return 1;
        if(oneCount == 2 && threeCount == 2) return 2;
        if(oneCount == 2 && fourCount == 2) return 3;
        return 4;
    }

    private static int getAreaOfType1() {
        int a = 0;
        int f = 0;
        int c = 0;
        int d = 0;

        for(int i = 0; i < commandArr.length; i++) {
            String[] arr = commandArr[i].split(" ");
            String command = arr[0];
            int length = Integer.parseInt(arr[1]);

            if(command.equals("4")) {
                a = length;
                continue;
            }

            if(command.equals("1")) {
                f = length;
                continue;
            }

            if(i-1 < 0) {
                String[] preArr = commandArr[commandArr.length - 1].split(" ");
                String preCommand = preArr[0];
                int preLength = Integer.parseInt(preArr[1]);

                if(command.equals("2") && preCommand.equals("3")) {
                    d = length;
                    c = preLength;
                }

                continue;
            }

            String[] preArr = commandArr[i - 1].split(" ");
            String preCommand = preArr[0];
            int preLength = Integer.parseInt(preArr[1]);

            if(command.equals("2") && preCommand.equals("3")) {
                d = length;
                c = preLength;
            }
        }

        return a * f - c * d;
    }

    private static int getAreaOfType2() {
        int a = 0;
        int b = 0;
        int d = 0;
        int e = 0;

        for(int i = 0; i < commandArr.length; i++) {
            String[] arr = commandArr[i].split(" ");
            String command = arr[0];
            int length = Integer.parseInt(arr[1]);

            if(command.equals("4")) {
                a = length;
                continue;
            }

            if(command.equals("2")) {
                b = length;
                continue;
            }

            if(i-1 < 0) {
                String[] preArr = commandArr[commandArr.length - 1].split(" ");
                String preCommand = preArr[0];
                int preLength = Integer.parseInt(preArr[1]);

                if(command.equals("3") && preCommand.equals("1")) {
                    e = length;
                    d = preLength;
                }

                continue;
            }

            String[] preArr = commandArr[i - 1].split(" ");
            String preCommand = preArr[0];
            int preLength = Integer.parseInt(preArr[1]);

            if(command.equals("3") && preCommand.equals("1")) {
                e = length;
                d = preLength;
            }
        }

        return a * b - d * e;
    }

    private static int getAreaOfType3() {
        int a = 0;
        int b = 0;
        int d = 0;
        int e = 0;

        for(int i = 0; i < commandArr.length; i++) {
            String[] arr = commandArr[i].split(" ");
            String command = arr[0];
            int length = Integer.parseInt(arr[1]);

            if(command.equals("2")) {
                d = length;
                continue;
            }

            if(command.equals("3")) {
                e = length;
                continue;
            }

            if(i-1 < 0) {
                String[] preArr = commandArr[commandArr.length - 1].split(" ");
                String preCommand = preArr[0];
                int preLength = Integer.parseInt(preArr[1]);

                if(command.equals("1") && preCommand.equals("4")) {
                    b = length;
                    a = preLength;
                }

                continue;
            }

            String[] preArr = commandArr[i - 1].split(" ");
            String preCommand = preArr[0];
            int preLength = Integer.parseInt(preArr[1]);

            if(command.equals("1") && preCommand.equals("4")) {
                b = length;
                a = preLength;
            }
        }

        return d * e - a * b;
    }

    private static int getAreaOfType4() {
        int b = 0;
        int c = 0;
        int e = 0;
        int f = 0;

        for(int i = 0; i < commandArr.length; i++) {
            String[] arr = commandArr[i].split(" ");
            String command = arr[0];
            int length = Integer.parseInt(arr[1]);

            if(command.equals("3")) {
                e = length;
                continue;
            }

            if(command.equals("1")) {
                f = length;
                continue;
            }

            if(i-1 < 0) {
                String[] preArr = commandArr[commandArr.length - 1].split(" ");
                String preCommand = preArr[0];
                int preLength = Integer.parseInt(preArr[1]);

                if(command.equals("4") && preCommand.equals("2")) {
                    c = length;
                    b = preLength;
                }

                continue;
            }

            String[] preArr = commandArr[i - 1].split(" ");
            String preCommand = preArr[0];
            int preLength = Integer.parseInt(preArr[1]);

            if(command.equals("4") && preCommand.equals("2")) {
                c = length;
                b = preLength;
            }
        }

        return e * f - b * c;
    }
}