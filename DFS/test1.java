import java.io.PrintWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class test1 {
    static final int MAX = 100;
    int G[][] = new int[MAX][MAX];
    int n = 0; // số dỉnh của đồ thị
    int free[] = new int[MAX];// mảng đánh dấu đỉnh đã được thăm.

    void Init() {
        File file = new File(getClass().getResource("dfs.in").getPath());
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            n = Integer.parseInt(reader.readLine());
            for (int i = 1; i <= n; i++) {
                String[] aLineOfMatrix = reader.readLine().split("\\s+"); // lấy chữ cái đầu tiên của từng từ
                for (int j = 1; j <= n; j++) {
                    G[i][j] = Integer.parseInt(aLineOfMatrix[j - 1]);// index của J bắt đầu từ 0.
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    void DFS(int v) {
        System.out.print(v + " ");
        free[v] = 1;
        for (int u = 1; u <= n; u++) {
            if (G[v][u] == 1 && free[u] == 0)
                DFS(u);
        }
    }

    public void test1() {
        Init();
        for (int i = 1; i <= n; i++) {
            free[i] = 0;
        }
        System.out.print("\n");
        for (int i = 1; i <= n; i++)
            if (free[i] == 0) {
                DFS(i);
            }
    }

    public static void main(String[] args) {
        System.out.println("DFS");
        test1 t = new test1();
        t.test1();
    }
}