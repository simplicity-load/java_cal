public class Cal {

    public Cal() { }

    public Year[] getMonth(int month, int year)
    {
        return getRangeOfMonths(month, year, month, year);
    }


    // How the first day of the first year starts out
    private static final int initial_day = 1;
    private static final int initial_week_position = 6;
    private static final int initial_month = 1;
    private static final int initial_year = 1;

    public Year[] getRangeOfMonths(int beginMonth, int beginYear, int endMonth, int endYear)
    {
        if (beginMonth < 0 || endMonth < 0 ||
            beginMonth > 12 || endMonth > 12 ||
            beginYear < 0 || endYear < 0 ||
            beginYear > endYear ||
            (beginMonth > endMonth && beginYear == endYear)) {

            System.out.println("You inputted wrong actual parameters for the Calendar");
            System.exit(1);
        }
        // We add one since were going to want to inclusively calculate years
        int number_of_years = endYear - beginYear + 1;
        Year[] yearArray = new Year[number_of_years];


        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int countDay = initial_day;
        int countWeekPosition = initial_week_position;
        int countMonth = initial_month;
        int countYear = initial_year;
        int counter = 0;
        while (countYear <= endYear) {

            if (countYear <= 1752 && countYear % 4 == 0)
                daysInMonth[1] = 29;
            else if((countYear % 4 == 0 && countYear % 100 != 0) || countYear % 400 == 0)
                daysInMonth[1] = 29;
            else
                daysInMonth[1] = 28;

            Year year = new Year(countYear);
            while (countMonth <= 12) {
                Month month = new Month(countMonth);
                Week week = new Week();
                while (countDay <= daysInMonth[countMonth-1]) {

                    if (countWeekPosition == 7) {
                        countWeekPosition = 1;
                        if (countYear >= beginYear && countMonth >= beginMonth || countYear > beginYear) {
                            week.setDay(new Day(countDay), 7);
                            month.addWeek(new Week(week.getWeek()));
                            week.clear();
                        }
                    }
                    else if (!(countYear == 1752 && countMonth == 9 && countDay > 2 && countDay < 14)) {
                        if (countYear >= beginYear && countMonth >= beginMonth || countYear > beginYear)
                            week.setDay(new Day(countDay), countWeekPosition);
                        countWeekPosition++;
                    }
                    countDay++;
                }
                if (countYear >= beginYear && countMonth >= beginMonth || countYear > beginYear) {
                    month.addWeek(new Week(week.getWeek()));
                    year.setMonth(new Month(month.getMonth(), countMonth), countMonth);
                    month.clear();
                }
                if (countMonth >= endMonth && countYear == endYear)
                    break;
                countDay = 1;
                countMonth++;
            }
            if (countYear >= beginYear) {
                yearArray[countYear-beginYear] = new Year(year.getYear(), countYear);
                year.clear();
            }

            if (countYear == endYear)
                break;

            countMonth = 1;
            countYear++;
        }

        return yearArray;
    }

    /*
    public static void main(String[] args)
    {
        Cal col = new Cal();
        Year[] yArr = col.getMonth(3, 2022);
        for (Year y : yArr) {
            System.out.printf("Year: %d\n", y.getYearNumber());
            for (Month m : y.getYear()) {
                System.out.println("M");
                if(m != null)
                    for (Week w : m.getMonth()) {
                        System.out.println("W");
                        if(w != null)
                            for (Day d : w.getWeek())
                                if(d != null)
                                    System.out.printf(" %2d ", d.getDay());
                                else
                                    System.out.printf("    ");
                    }
            }
        }

    }
    */
}
