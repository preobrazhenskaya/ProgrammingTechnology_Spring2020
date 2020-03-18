package Part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1_4 {
    private static Scanner in = new Scanner(System.in);

    private static List<Integer> readArray() {
        System.out.print("Enter count of elements: ");
        int n = in.nextInt();
        List<Integer> arr = new ArrayList<>();
        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            arr.add(in.nextInt());
        }
        return arr;
    }

    private static void writeArray(List<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
    }

    private static List<Integer> evenElems(List<Integer> arr) {
        List<Integer> rezArr = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) % 2 == 0) {
                rezArr.add(arr.get(i));
            }
        }
        return rezArr;
    }

    private static List<Integer> notEvenElems(List<Integer> arr) {
        List<Integer> rezArr = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) % 2 != 0) {
                rezArr.add(arr.get(i));
            }
        }
        return rezArr;
    }

    private static void task1() {
        List<Integer> arr = readArray();
        List<Integer> evenElems = evenElems(arr);
        List<Integer> notEvenElems = notEvenElems(arr);
        System.out.println("Even elements:");
        writeArray(evenElems);
        System.out.println("\nNot even elements:");
        writeArray(notEvenElems);
    }

    private static List<Integer> multiple3or9(List<Integer> arr) {
        List<Integer> rezArr = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) % 3 == 0 || arr.get(i) % 9 == 0) {
                rezArr.add(arr.get(i));
            }
        }
        return rezArr;
    }

    private static void task2() {
        List<Integer> arr = readArray();
        List<Integer> rezArr = multiple3or9(arr);
        System.out.println("Elements multiple 3 or 9:");
        writeArray(rezArr);
    }

    private static List<Integer> multiple5or10(List<Integer> arr) {
        List<Integer> rezArr = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) % 5 == 0 || arr.get(i) % 10 == 0) {
                rezArr.add(arr.get(i));
            }
        }
        return rezArr;
    }

    private static void task3() {
        List<Integer> arr = readArray();
        List<Integer> rezArr = multiple5or10(arr);
        System.out.println("Elements multiple 5 or 10:");
        writeArray(rezArr);
    }

    private static List<Integer> checkPrimeArray(List<Integer> arr) {
        List<Integer> rezArr = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (checkPrimeValue(arr.get(i))) {
                rezArr.add(arr.get(i));
            }
        }
        return rezArr;
    }

    private static boolean checkPrimeValue(Integer n) {
        for (int j = 0; j <= Math.sqrt(n); j++) {
            if (n % j == 0) {
                return false;
            }
        }
        return true;
    }

    private static void task5() {
        List<Integer> arr = readArray();
        List<Integer> rezArr = checkPrimeArray(arr);
        System.out.println("Prime values:");
        writeArray(rezArr);
    }

    private static void writeNumber(int num) {
        switch (num) {
            case 0:
                System.out.println("Ноль");
                break;
            case 1:
                System.out.println("Один");
                break;
            case 2:
                System.out.println("Два");
                break;
            case 3:
                System.out.println("Три");
                break;
            case 4:
                System.out.println("Четыре");
                break;
            case 5:
                System.out.println("Пять");
                break;
            case 6:
                System.out.println("Шесть");
                break;
            case 7:
                System.out.println("Семь");
                break;
            case 8:
                System.out.println("Восемь");
                break;
            case 9:
                System.out.println("Девять");
                break;
        }
    }

    private static void task7() {
        System.out.print("Enter number (0 <= number <= 9): ");
        int num = in.nextInt();
        writeNumber(num);
    }

    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
//        task4(); // todo
//        task5();
//        task6(); // todo
        task7();
    }
}
