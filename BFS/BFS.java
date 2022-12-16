import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    public static int n, m, start;
    public static Queue queues = new LinkedList<Integer>();
    public static int[][] arrays;
    public static int[] free;
    public static int[] back;
    public static StringBuffer stringBuffer = new StringBuffer();

    public static void bfs(int s) {
        queues.add(s);
        free[s] = 1;
        while (!queues.isEmpty()) {
            int u = (int) queues.poll();
            for (int v = 0; v < n; v++) {
                if (free[v] == 0 && arrays[u][v] == 1) {
                    free[v] = 1;
                    back[v] = u;
                    queues.add(v);
                    stringBuffer.append(", " + v);
                }
            }
        }
    }

    public static void readFile() throws IOException {
        try {
            FileReader file = new FileReader("bfs.in");
            try (BufferedReader bufferedReader = new BufferedReader(file)) {
                String line = bufferedReader.readLine();
                try {
                    n = Integer.parseInt(line.split(" ")[0]);

                    arrays = new int[n + 1][n + 1];
                    free = new int[n];
                    back = new int[n];
                    stringBuffer.append(start);
                    for (int i = 0; i < n; i++) {
                        line = bufferedReader.readLine();
                        for (int j = 0; j < n; j++) {
                            arrays[i][j] = Integer.parseInt(line.split(" ")[j]);

                        }
                    }
                } catch (NullPointerException e) {
                    // TODO: handle exception
                }
            } catch (FileNotFoundException e) {
                throw e;
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        readFile();

        for (int i = 0; i < n; i++) {
            free[i] = 0;
        }
        Scanner sc = new Scanner(System.in);

        bfs(start);

        try {
            PrintWriter printWriter = new PrintWriter("bfs.out");
            System.out.println("From node 0 you can visit nodes: " +
                    stringBuffer.toString());
            printWriter.println("From node 0 you can visit nodes: " +
                    stringBuffer.toString());

            System.out.print("Enter node you want to go: ");
            m = sc.nextInt();
            System.out.print(m);
            printWriter.print(m);
            while (start != m) {
                m = back[m];
                System.out.print("-" + m);
                printWriter.print("-" + m);
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            // TODO: handle exception
        }
    }
}