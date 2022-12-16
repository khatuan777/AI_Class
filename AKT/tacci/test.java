package AKT.tacci;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Node {
    private int g;
    private int h;
    private int f;
    private List<Node> Next = new ArrayList<Node>();
    private Node came_from = null;

    public List<Node> getNext() {
        return Next;
    }

    public void setNext(List<Node> next) {
        Next = next;
    }

    public Node getCame_from() {
        return came_from;
    }

    public void setCame_from(Node came_from) {
        this.came_from = came_from;
    }

    private int[][] a = new int[3][3];

    public void makelink(Node node) {
        this.Next.add(node);
        node.Next.add(this);
    }

    public int Estimate(Node target) {
        int tmp = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.getA()[i][j] != target.getA()[i][j]) {
                    tmp++;
                }
            }
        }
        return tmp;
    }

    public Node(int[][] pos) {
        this.a = pos;
        Next.clear();
        this.came_from = null;
    }

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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(this.a[i][j] + " ");
            }
            System.out.println('\n');
        }
        System.out.println(toString());
    }

}

public class test {
    public static int[][] begin = { { 1, 0, 7 }, { 5, 4, 8 }, { 2, 3, 6 } };
    public static int[][] end = { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 0 } };

    // public static List<Node> open = new ArrayList<Node>();

    static Stack<Node> akt(Node start, Node target) {

        if (target == null) {
            System.out.println("No answer");
        }
        List<Node> close = new ArrayList<Node>();
        List<Node> open = new ArrayList<Node>();
        open.clear();
        close.clear();
        target.setA(end);
        start.setG(0);
        start.setA(begin);
        start.setH(start.Estimate(target));
        start.setF(start.getG() + start.getH());
        open.add(start);
        while (!open.isEmpty()) {
            Node current = open.get(0);
            for (int i = 0; i < open.size(); i++) {
                if (open.get(i).getF() < current.getF()) {
                    current = open.get(i);
                }
            }
            open.remove(current);
            close.add(current);
            if (Arrays.deepEquals(current.getA(), target.getA())) {
                open.clear();
                close.clear();
                return reconstruct(start, target);
            } else {
                // tìm những đỉnh trong current.Next
                int I = 0, J = 0;
                // Tim Node bang 0
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (current.getA()[i][j] == 0) {
                            I = i;
                            J = j;
                        }
                    }
                }
                System.out.println(I + " " + J);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (Math.abs(I - i) + Math.abs(J - j) == 1) {
                            int[][] fake = new int[3][3];
                            fake = current.getA();
                            int temp = 0;
                            temp = fake[I][J];
                            fake[I][J] = fake[i][j];
                            fake[i][j] = temp;
                            Node s = new Node();
                            s.setA(fake);
                            s.setG(current.getG());
                            s.setH(s.Estimate(target));
                            s.setF(s.getG() + s.getH());
                            current.getNext().add(s);
                        }
                    }
                }

                for (Node i_Node : open) {
                    if (close.contains(i_Node)) {
                        continue;
                    }
                    int tmp_current_g = current.getG() + current.Estimate(target);
                    if (!open.contains(i_Node) || tmp_current_g < i_Node.getG()) {
                        i_Node.setCame_from(current);
                        i_Node.setG(tmp_current_g);
                        i_Node.setH(i_Node.Estimate(target));
                        i_Node.setF(i_Node.getG() + i_Node.getH());
                        if (!open.contains(i_Node)) {
                            open.add(i_Node);
                        }
                    }
                }
                // // So sánh 2 mảng 2 chiều
                // if (Arrays.deepEquals(current.getA(), target.getA())) {
                // current.xuat();
                // System.out.println(open);
                // break;

                // } else {
                // bac++;
                // open.remove(current);
                // close.add(current);

                // }

            }
        }
        return null;
    }

    static Stack<Node> reconstruct(Node start, Node target) {
        Stack<Node> path = new Stack<Node>();
        path.clear();
        Node tmp = target;
        while (tmp != null) {
            path.push(tmp);
            tmp = tmp.getCame_from();
        }
        return path;
    }

    // static void chep2() {
    // for (int i = 0; i < 3; i++) {
    // for (int j = 0; j < 3; j++) {
    // begin[i][j] = fake[i][j];
    // }
    // }
    // }

    static void saochep(Node current, int[][] fake) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fake[i][j] = current.getA()[i][j];
            }
        }
    }

    static void swap(int x, int y) {
        int tmp = 0;
        tmp = x;
        x = y;
        y = tmp;
    }

    public static void main(String[] args) {
        Node start = new Node();
        Node target = new Node();
        akt(start, target).forEach(e -> e.xuat());
    }
}