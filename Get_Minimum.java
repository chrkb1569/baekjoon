import java.io.*;
import java.util.StringTokenizer;

public class Get_Minimum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        int sum = 0;

        if(str.contains("+") && str.contains("-")) {
            StringTokenizer st = new StringTokenizer(str, "-");
            String token = st.nextToken();
            int total = 0;

            if(token.contains("+")) {
                StringTokenizer sk = new StringTokenizer(token, "+");
                while(sk.hasMoreTokens()) {
                    sum += Integer.parseInt(sk.nextToken());
                }
            }
            else {
                sum = Integer.parseInt(token);
            }

            while(st.hasMoreTokens()) {
                token = st.nextToken();

                if(token.contains("+")) {
                    StringTokenizer sk = new StringTokenizer(token, "+");
                    while(sk.hasMoreTokens()) {
                        sum -= Integer.parseInt(sk.nextToken());
                    }
                }
                else {
                    sum -= Integer.parseInt(token);
                }
            }
        }

        else if(str.contains("+") && !(str.contains("-"))) {
            StringTokenizer st = new StringTokenizer(str, "+");
            while(st.hasMoreTokens()) {
                sum += Integer.parseInt(st.nextToken());
            }
        }

        else if(!(str.contains("+")) && str.contains("-")) {
            StringTokenizer st = new StringTokenizer(str, "-");
            sum = Integer.parseInt(st.nextToken());

            while(st.hasMoreTokens()) {
                sum -= Integer.parseInt(st.nextToken());
            }
        }

        else if(!(str.contains("+")) && !(str.contains("-"))) {
            sum = Integer.parseInt(str);
        }

        bw.write(sum + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
