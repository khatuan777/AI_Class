package count_money;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class count_money {

    public static int n;
    public static StringBuffer result = new StringBuffer();

    public static Integer[] values = { 500, 100, 50, 10, 20, 200 };
    public static ArrayList<Integer> list = new ArrayList<>(Arrays.asList(values));

    public static void count_Money(StringBuffer result, ArrayList list, int n) {

        while (!list.isEmpty()) {
            int x = selectMax(list);
            list.remove(Integer.valueOf(x));
            if (n / x != 0) {
                int a = n / x;
                result.append(a + ": " + x + " $" + '\n');
                n = n % x;
            }
        }
        // result.append(1 + ": " + n % x);
    }

    public static int selectMax(ArrayList list) {
        int max = (int) list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if ((int) list.get(i) > max) {
                max = (int) list.get(i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.print("Enter the money: ");
        try (Scanner sc = new Scanner(System.in)) {
            n = sc.nextInt();
        }
        count_Money(result, list, n);
        System.out.println(result.toString());

    }
}
