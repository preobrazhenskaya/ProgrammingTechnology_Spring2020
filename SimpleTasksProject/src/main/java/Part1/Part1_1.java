package Part1;

import java.util.Scanner;

public class Part1_1 {
    private static Scanner in = new Scanner(System.in);

    private static double squardOfSum(int a, int b) {
        return Math.pow(a + b, 2);
    }

    private static double sumOfSquard(int a, int b) {
        return Math.pow(a, 2) + Math.pow(b, 2);
    }

    private static void task1() {
        System.out.println("Enter a:");
        int a = in.nextInt();
        System.out.println("Enter b:");
        int b = in.nextInt();
        String rez = sumOfSquard(a, b) > squardOfSum(a, b) ? "sumOfSquard > squardOfSum" : "squardOfSum > sumOfSquard";
        System.out.println(rez);
    }

    private static int allowance(int percent, int salary) {
        return salary / 100 * percent;
    }

    private static int salarySum(int allowance, int salary) {
        return salary + allowance;
    }

    private static void task2() {
        System.out.println("Enter salary:");
        int salary = in.nextInt();
        System.out.println("Enter experience:");
        int experience = in.nextInt();
        int allowancePercent = 0;
        if (experience >= 2 && experience < 5) {
            allowancePercent = 2;
        } else if (experience >= 5 && experience <= 10) {
            allowancePercent = 5;
        }
        int allowance = allowance(allowancePercent, salary);
        System.out.println("Allowance: " + allowance);
        System.out.println("Salary sum: " + salarySum(allowance, salary));
    }

    private static double distance(int x, int y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    private static void task3() {
        System.out.println("Enter point A(x, y):");
        System.out.print("x: ");
        int ax = in.nextInt();
        System.out.print("y: ");
        int ay = in.nextInt();
        System.out.println("Enter point B(x, y):");
        System.out.print("x: ");
        int bx = in.nextInt();
        System.out.print("y: ");
        int by = in.nextInt();
        if (distance(ax, ay) < distance(bx, by)) {
            System.out.println("Point A is farthest than point B");
        } else if (distance(ax, ay) > distance(bx, by)) {
            System.out.println("Point B is farthest than point A");
        } else {
            System.out.println("Point A and point B are equally farthest");
        }
    }

    private static boolean checkTriangle(int a, int b, int c) {
        return c * c == a * a + b * b || a * a == c * c + b * b || b * b == a * a + c * c;
    }

    private static void task4() {
        System.out.print("Enter side a: ");
        int a = in.nextInt();
        System.out.print("Enter side b: ");
        int b = in.nextInt();
        System.out.print("Enter side c: ");
        int c = in.nextInt();
        if (checkTriangle(a, b, c)) {
            System.out.println("It's right triangle");
        } else {
            System.out.println("It isn't right triangle");
        }
    }

    private static int squardOrNot(int value) {
        if (value > 0) {
            return value * value;
        }
        return value;
    }

    private static void task5() {
        int[] values;
        values = new int[3];
        for (int i = 0; i < values.length; i++) {
            System.out.print("Enter value " + i + ": ");
            values[i] = in.nextInt();
        }
        for (int elem : values) {
            System.out.println(squardOrNot(elem) + " ");
        }
    }

    private static boolean checkValue(int value) {
        return value >= 1 && value <= 12;
    }

    private static String season(int value) {
        if (value == 1 || value == 2 || value == 12) {
            return "Winter";
        } else if (value >= 3 && value <= 5) {
            return "Spring";
        } else if (value >= 6 && value <= 8) {
            return "Summer";
        } else {
            return "Autumn";
        }
    }

    private static void task6() {
        System.out.println("Enter value from 1 to 12:");
        int value = in.nextInt();
        System.out.println(season(value));
    }

    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
//        task4();
//        task5();
        task6();
    }
}
