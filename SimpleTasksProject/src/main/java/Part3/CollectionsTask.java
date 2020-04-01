package Part3;

import java.util.*;

public class CollectionsTask {
    private static Scanner in = new Scanner(System.in);

    private static ArrayList<Integer> readArrayList() {
        ArrayList<Integer> array = new ArrayList<Integer>();
        System.out.print("Enter ArrayList size: ");
        int n = in.nextInt();
        System.out.println("Enter values:");
        for (int i = 0; i < n; i++) {
            array.add(in.nextInt());
        }
        return array;
    }

    private static HashMap<Integer, String> readHashMap() {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        System.out.print("Enter Map size: ");
        int n = in.nextInt();
        System.out.println("Enter values:");
        for (int i = 0; i < n; i++) {
            map.put(i, in.nextLine());
        }
        return map;
    }

    private static ArrayList<Integer> descendingSorting(ArrayList<Integer> array) {
        Collections.sort(array, Collections.<Integer>reverseOrder());
        return array;
    }

    private static void task1() {
        ArrayList<Integer> array = readArrayList();
        array = descendingSorting(array);
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i));
        }
    }

    private static int arraySum(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    private static void task2() {
        int[] array;
        System.out.print("Enter Array size: ");
        int n = in.nextInt();
        array = new int[n];
        System.out.println("Enter values:");
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        System.out.println(arraySum(array));
    }

    private static boolean checkValue(Map<Integer, String> map, String value) {
        return map.containsValue(value);
    }

    private static void task3() {
        Map<Integer, String> map = readHashMap();
        System.out.print("Enter check value: ");
        String value = in.nextLine();
        System.out.printf("", checkValue(map, value));
    }

    private static int findElem(ArrayList<Integer> array, int elem) {
        return array.indexOf(elem);
    }

    private static void task5() {
        ArrayList<Integer> array = readArrayList();
        System.out.print("Enter check value: ");
        int value = in.nextInt();
        int index = findElem(array, value);
        if (index == -1) {
            System.out.println("Elem not found");
        } else {
            System.out.println("Elem found: " + index);
        }
    }

    private static void task4() {
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        System.out.print("Enter ArrayList size: ");
        int n = in.nextInt();
        System.out.println("Enter values:");
        for (int i = 0; i < n; i++) {
            linkedList.add(in.nextInt());
        }
        System.out.println("Add first:");
        int elem = in.nextInt();
        linkedList.addFirst(elem);
        System.out.println("Add last:");
        elem = in.nextInt();
        linkedList.addLast(elem);
        ListIterator<Integer> itr = linkedList.listIterator();
        while (itr.hasNext())
            System.out.println(itr.next());
    }

    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
        task4();
//        task5();
    }
}
