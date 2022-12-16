import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DFS {
    public static int n, m, start;
    public static Queue queues = new LinkedList<Integer>();
    public static int[][] arrays;
    public static int[] free;
    public static int[] back;
    public static StringBuffer stringBuffer = new StringBuffer();

    public DFS() {
        super();
    }

    void readFile() throws IOException {
        try {
            FileReader file = new FileReader("dfs.in");
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

    void dfs(int v) {
        System.out.print(v + " ");
        free[v] = 1;
        for (int u = 1; u <= n; u++) {
            if (arrays[v][u] == 1 && free[u] == 0)
                dfs(u);
        }
    }

    void DFS() throws IOException {
        readFile();
        for (int i = 0; i < n; i++) {
            free[i] = 0;
        }
        System.out.print("\n");
        for (int i = 0; i < n; i++)
            if (free[i] == 0) {
                dfs(i);
            }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("DFS");
        DFS d = new DFS();
        d.DFS();
    }
}
