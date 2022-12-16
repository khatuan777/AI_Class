package AKT.tacci;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Node {
    private int g;
    private int h;
    private int f;

    private int[][] a = new int[3][3];

    public Node() {
    }

    public int getG() {
        return g;
    }

    public int[][] getA() {
        return a;
    }

    public void setA(int[][] a) {
        this.a = a;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    @Override
    public String toString() {
        return "[g=" + g + ", h=" + h + ", f=" + f + "]";
    }

    public void xuat() {
        System.out.println(toString());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(this.a[i][j] + " ");
            }
            System.out.println('\n');
        }
    }

}

public class Tacci {
    public static int[][] begin = { { 1, 0, 7 }, { 5, 4, 8 }, { 2, 3, 6 } };
    public static int[][] end = { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 0 } };
    // public static List<Node> open = new ArrayList<Node>();
    public static List<Node> close = new ArrayList<Node>();
    public static List<Node> open = new ArrayList<Node>();

    static void akt(Node start, Node target) {
        if (target == null) {
            System.out.println("No answer");
            return;
        }
        target.setA(end);
        start.setG(0);
        start.setA(begin);
        start.setH(Estimate(start, target));
        start.setF(start.getG() + start.getH());
        // Đưa start vào open list
        open.add(start);
        while (!open.isEmpty()) {
            Node current = open.get(0);
            for (int i = 0; i < open.size(); i++) {
                if (open.get(i).getF() < current.getF()) {
                    current = open.get(i);
                }
            }

            if (Arrays.deepEquals(current.getA(), target.getA())) {
                close.add(current);
                close.forEach(e -> e.xuat());
                break;
            } else if (open.isEmpty()) {
                System.out.println("No solution");
                break;
            } else {
                open.remove(current);
                close.add(current);
                int I = 0, J = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (current.getA()[i][j] == 0) {
                            I = i;
                            J = j;
                        }
                    }
                }
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (Math.abs(I - i) + Math.abs(J - j) == 1) {
                            int[][] fake = new int[3][3];
                            saochep(current, fake);
                            int temp = 0;
                            temp = fake[I][J];
                            fake[I][J] = fake[i][j];
                            fake[i][j] = temp;
                            Node s = new Node();
                            s.setA(fake);
                            s.setG(current.getG() + 1);
                            s.setH(Estimate(s, target));
                            s.setF(s.getG() + s.getH());
                            if (!close.contains(s) && !open.contains(s)) {
                                open.add(s);
                            }
                        }
                    }
                }
            }

        }

    }

    static void saochep(Node current, int[][] fake) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fake[i][j] = current.getA()[i][j];
            }
        }
    }

    static int Estimate(Node start, Node target) {
        int tmp = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (start.getA()[i][j] != target.getA()[i][j]) {
                    tmp++;
                }
            }
        }
        return tmp;
    }

    public static void main(String[] args) {
        Node start = new Node();
        Node target = new Node();
        akt(start, target);
    }
}