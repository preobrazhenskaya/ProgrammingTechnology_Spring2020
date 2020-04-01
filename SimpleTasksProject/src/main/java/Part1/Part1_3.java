package Part1;

import java.util.Scanner;

public class Part1_3 {
    private static Scanner in = new Scanner(System.in);

    private static void task1() {
        System.out.println("Enter A and B (A < B):");
        System.out.print("A: ");
        int a = in.nextInt();
        System.out.print("B: ");
        int b = in.nextInt();
        for (int i = a; i <= b; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        int count = b - a + 1;
        System.out.println("Count: " + count);
    }

    private static void task2() {
        System.out.println("Enter A and B (A < B):");
        System.out.print("A: ");
        int a = in.nextInt();
        System.out.print("B: ");
        int b = in.nextInt();
        for (int i = b - 1; i > a; i--) {
            System.out.print(i + " ");
        }
        System.out.println();
        int count = b - a + 1;
        System.out.println("Count: " + count);
    }

    private static int calcPow(int a, int n) {
        int rez = 1;
        for (int i = 0; i < n; i++) {
            rez *= a;
        }
        return rez;
    }

    private static void task3() {
        System.out.print("Enter A: ");
        int a = in.nextInt();
        System.out.print("Enter N: ");
        int n = in.nextInt();
        int rez = calcPow(a, n);
        System.out.println(rez);
    }

    private static void task4() {
        System.out.print("Enter A: ");
        int a = in.nextInt();
        System.out.print("Enter N: ");
        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            int rez = calcPow(a, i);
            System.out.println(rez);
        }
    }

    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
        task4();
    }
}
