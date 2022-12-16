
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class GTS {
    private int cost;
    private StringBuffer tour;

    public GTS(int cost, StringBuffer tour) {
        this.cost = cost;
        this.tour = tour;
    }

    public GTS() {

    };

    public int getCost() {
        return this.cost;
    }

    public StringBuffer getTour() {
        return tour;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setTour(StringBuffer tour) {
        this.tour = tour;
    }

    public void GTS_1(int n, int start, int[][] arrays) {
        tour = new StringBuffer();
        // Truy vết
        tour.append(start);
        int[] visited = new int[n];
        for (int u = 0; u < n; u++) {
            visited[u] = 0;
        }
        int v = start;
        int w = 0;
        for (int i = 0; i < n - 1; i++) {
            visited[v] = 1;
            int min = Integer.MAX_VALUE;
            for (int x = 0; x < n; x++) {
                if (arrays[v][x] < min && visited[x] == 0) {
                    min = arrays[v][x];
                    w = x;
                }
            }
            cost = cost + min;
            tour.append("-->" + w);
            v = w;
            visited[v] = 1;
        }
        // v = w;
        tour.append("-->" + start);
        cost = cost + arrays[v][start];
        System.out.println(tour.toString());
        System.out.println(cost);
    }
}

public class GTS_2 {
    // GTS-1

    public static int n;

    public static int[][] arrays;

    // GTS-2

    public static ArrayList<Integer> bestTour = new ArrayList<Integer>();

    public static void readFile() throws IOException {
        try {
            FileReader file = new FileReader("GTS_2.in");
            BufferedReader bufferedReader = new BufferedReader(file);

            String line = bufferedReader.readLine();
            try {
                n = Integer.parseInt(line.split(" ")[0]);
                arrays = new int[n + 1][n + 1];

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void GTS_22(int[][] arrays, int[] p) {
        int bestCost = Integer.MAX_VALUE;
        String bestTour = "";
        for (int i = 0; i < p.length; i++) {
            GTS gts = new GTS();
            gts.GTS_1(n, p[i], arrays);
            int x = gts.getCost();
            if (x < bestCost) {
                bestCost = gts.getCost();
                bestTour = gts.getTour().toString();
            }

        }
        System.out.println("Best tour cost is " + bestCost + " with best tour:  " + bestTour);
    }

    public static void main(String[] args) throws IOException {
        readFile();
        int[] p = { 3, 2, 4, 5 };
        // GTS_22(n, arrays, p);
        GTS_22(arrays, p);
    }
}
