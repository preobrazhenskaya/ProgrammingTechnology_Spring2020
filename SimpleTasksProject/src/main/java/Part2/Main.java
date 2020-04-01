package Part2;

public class Main {
    private static void testDate() {
        Date date1 = new Date();
        date1.printData();
        Date date2 = new Date(2, 1, 2019);
        date2.printData();
        date2.yearPlusOne();
        date2.printData();
        date2.dateMinusTwoDays();
        date2.printData();
        date2.monthPlusOne();
        date2.printData();
        if (date2.checkEqualityMonthAndDay()) {
            System.out.println("Month == Day");
        } else {
            System.out.println("Month != Day");
        }
    }

    public static void main(String[] args) {
        testDate();
    }
}
