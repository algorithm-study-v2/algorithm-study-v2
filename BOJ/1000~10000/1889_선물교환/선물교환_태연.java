import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 선물교환_태연 {
    
    /*
     * - 452ms
     *
     * - 친구 2명한테 선물 못받으면 죽임
     */

    static final long MUL = 1_000_000;

    static int f1;
    static int f2;
    static int personLeft;

    static StringTokenizer st;

    static void parseFriend(long hash){
        f1 = (int)(hash%MUL);
        f2 = (int)(hash/MUL);
    }

    static void kill(Queue<Integer> toDelete, boolean[] dead, int toKill){
        dead[toKill] = true;
        toDelete.add(toKill);
        personLeft--;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = personLeft = Integer.parseInt(br.readLine());

        long[] toGive = new long[n+1];
        int[] degree = new int[n+1];
        boolean[] dead = new boolean[n+1];

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            degree[n1]++;
            degree[n2]++;
            toGive[i] = n1*MUL+n2;
        }

        Queue<Integer> toDelete = new LinkedList<>();

        for(int i=1; i<=n; i++){
            if(degree[i] < 2){
                kill(toDelete, dead, i);
            }
        }

        while(!toDelete.isEmpty()){
            parseFriend(toGive[toDelete.poll()]);
            if(!dead[f1] && (--degree[f1] < 2)){
                kill(toDelete, dead, f1);
            }
            if(!dead[f2] && (--degree[f2] < 2)){
                kill(toDelete, dead, f2);
            }
        }

        System.out.println(personLeft);

        if(personLeft>0){
            StringBuilder sb = new StringBuilder();
            for(int i=1; i<=n; i++){
                if(!dead[i]){
                    sb.append(i).append(' ');
                }
            }
            System.out.print(sb);
        }
    }
}
