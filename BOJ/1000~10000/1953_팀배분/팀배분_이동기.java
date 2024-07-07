import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 80ms
 */
public class Main {
    static int n;
    static ArrayList<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int hateCnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < hateCnt; j++)
                adjList[i].add(Integer.parseInt(st.nextToken()));
        }
        ArrayList<Integer> whiteList = new ArrayList<>();
        ArrayList<Integer> blueList = new ArrayList<>();

        boolean[] visited = new boolean[n + 1];
        Queue<Info> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;

            queue.add(new Info(false, i));
            visited[i] = true;
            blueList.add(i);
            while (!queue.isEmpty()) {
                Info info = queue.poll();

                for (int hate : adjList[info.student]) {
                    if (!visited[hate]) {
                        boolean isWhite = !info.isWhite;
                        queue.add(new Info(isWhite, hate));
                        visited[hate] = true;

                        if (isWhite) whiteList.add(hate);
                        else blueList.add(hate);
                    }
                }
            }
        }

        Collections.sort(blueList);
        Collections.sort(whiteList);

        System.out.println(blueList.size());
        for (int i : blueList) System.out.print(i + " ");
        System.out.println("\n" + whiteList.size());
        for (int i : whiteList) System.out.print(i + " ");
    }

    static class Info {
        boolean isWhite;
        int student;

        Info(boolean isWhite, int student) {
            this.isWhite = isWhite;
            this.student = student;
        }
    }
}
