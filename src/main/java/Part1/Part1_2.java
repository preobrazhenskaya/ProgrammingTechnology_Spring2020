package Part1;

import java.util.Scanner;

public class Part1_2 {
    private static Scanner in = new Scanner(System.in);

    private static boolean multiple3(int number) {
        return number % 3 == 0;
    }

    private static boolean multiple5(int number) {
        return number % 5 == 0;
    }

    private static void task1() {
        System.out.println("Enter range:");
        int a = in.nextInt();
        int b = in.nextInt();
        for (int i = a; i <= b; i++) {
            int sum = 0;
            int value = i;
            while (value > 0) {
                sum += value % 10;
                value /= 10;
            }
            if (multiple3(i) && !multiple5(i) && multiple3(sum) && !multiple5(sum)) {
                System.out.println(i);
            }
        }
    }

    private static void task2() {
        System.out.println("Enter range:");
        int a = in.nextInt();
        int b = in.nextInt();
        for (int i = a; i <= b; i++) {
            if (multiple5(i)) {
                System.out.println(i);
            }
        }
    }

    private static boolean checkPowerOfTwo(int value) {
        double rez = Math.sqrt(value);
        return rez - Math.floor(rez) == 0;
    }

    private static void task3() {
        System.out.println("Enter value:");
        int value = in.nextInt();
        if (checkPowerOfTwo(value)) {
            System.out.println("Value is power of two");
        } else {
            System.out.println("Value isn't power of two");
        }
    }

    private static void task4() {
        System.out.println("Enter end of range:");
        int n = in.nextInt();
        System.out.print("0 1 ");
        int previousValue = 0;
        for (int i = 1; i <= n; ) {
            int value = i;
            i = value + previousValue;
            previousValue = value;
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
//        task4();
    }
}
