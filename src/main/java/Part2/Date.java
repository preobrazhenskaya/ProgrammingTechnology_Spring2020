package Part2;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date() { }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void yearPlusOne() {
        year += 1;
    }

    private int dayInMonth(int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else if (month == 2) {
            return 28;
        } else {
            return 30;
        }
    }

    public void dateMinusTwoDays() {
        if (day > 2) {
            day -= 2;
        } else {
            if (month == 1) {
                month = 12;
                year -= 1;
            } else {
                month -= 1;
            }
            day = dayInMonth(month);
        }
    }

    public void monthPlusOne() {
        if (month == 12) {
            month = 1;
            year += 1;
        } else {
            month += 1;
        }
    }

    public boolean checkEqualityMonthAndDay() {
        return month == day;
    }

    public void printData() {
        System.out.println("Date: " + day + "." + month + "." + year);
    }
}
