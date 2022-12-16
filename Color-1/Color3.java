
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class dinh {
    private int mau = 0;
    private int sodinh;
    private int bac;

    public dinh(int mau, int sodinh, int bac) {
        this.mau = mau;
        this.sodinh = sodinh;
        this.bac = bac;
    }

    public dinh() {
    }

    public int getMau() {
        return mau;
    }

    public int getBac() {
        return bac;
    }

    public int getSodinh() {
        return sodinh;
    }

    public void setSodinh(int sodinh) {
        this.sodinh = sodinh;
    }

    public void setBac(int bac) {
        this.bac = bac;
    }

    public void setMau(int mau) {
        this.mau = mau;
    }

    @Override
    public String toString() {
        return "dinh: " + sodinh + " co bac: " + bac + " mau: " + mau;
    }

}

public class Color3 {

    public static int n;

    public static int[] free;

    public static int[] back;

    public static int[][] arrays;

    public static List<dinh> danhsachDinh = new ArrayList<dinh>();

    public static void readFile() throws IOException {
        try {
            FileReader file = new FileReader("color.in");
            BufferedReader bufferedReader = new BufferedReader(file);

            String line = bufferedReader.readLine();
            try {
                n = Integer.parseInt(line.split(" ")[0]);

                arrays = new int[n + 1][n + 1];
                free = new int[n];
                back = new int[n];
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

    static boolean tim() {
        for (int i = 0; i < n; i++) {
            if (danhsachDinh.get(i).getBac() > 0)
                return true;
        }
        return false;
    }

    public static void color(int n, int[][] arrays) throws ClassCastException {
        for (int i = 0; i < n; i++) {
            dinh d = new dinh();
            int bac = 0;
            for (int j = 0; j < n; j++) {
                if (arrays[i][j] == 1) {
                    bac += 1;
                }
            }
            d.setSodinh(i);
            d.setBac(bac);
            danhsachDinh.add(d);
        }
        int mau = 1;
        int u = 0;
        while (tim()) {
            dinh max = danhsachDinh.get(0);
            for (dinh i : danhsachDinh) {
                if (i.getBac() > max.getBac() && i.getBac() > 0) {
                    max = i;
                }
            }
            max.setBac(0);
            if (max.getMau() == -mau) {
                mau = mau + 1;
            }
            max.setMau(mau);
            for (int j = 0; j < n; j++) {
                if (arrays[max.getSodinh()][j] == 1 && arrays[j][max.getSodinh()] == 1
                        && danhsachDinh.get(j).getBac() > 0) {
                    danhsachDinh.get(j).setMau(-mau);
                    danhsachDinh.get(j).setBac(danhsachDinh.get(j).getBac() - 1);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        readFile();
        color(n, arrays);
        danhsachDinh.forEach(e -> System.out.println(e.toString()));

    }

}
