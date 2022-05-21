public class Cal {

    public Cal() { }

    //TODO get only the specified month
    /*
    public Year[] getMonth(int month, int year)
    {
        return getRangeOfMonths(month, year, month, year);
    }
    */


    // How the first day of the first year starts out
    private static final int initial_day = 1;
    private static final int initial_week_position = 6;
    private static final int initial_month = 1;
    private static final int initial_year = 1;

    //TODO get a range of months including BEGIN and END limits
    //public Year[] getRangeOfMonths(int beginMonth, int beginYear, int endMonth, int endYear)
    public void getRangeOfMonths(int beginMonth, int beginYear, int endMonth, int endYear)
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
        while (countMonth < beginMonth || countYear < beginYear)
        {

            if (countYear <= 1752 && countYear % 4 == 0)
                daysInMonth[1] = 29;
            else if((countYear % 4 == 0 && countYear % 100 != 0) || countYear % 400 == 0)
                daysInMonth[1] = 29;
            else
                daysInMonth[1] = 28;


            if (countDay == daysInMonth[countMonth-1]) {
                if (countMonth == 12) {
                    countYear++;
                    countMonth = 1;
                }
                else
                    countMonth++;
                countDay = 1;
            } else
                countDay++;

            if (countWeekPosition == 7)
                countWeekPosition = 1;
            else
                if (!(countYear == 1752 && countMonth == 9 && countDay > 2 && countDay < 14))
                    countWeekPosition++;

            counter++;
        }

        if(beginYear > 1752)
            counter -= 11;

        // Start creating the actual Calendar we want
        while (countMonth <= endMonth || countYear <= endYear) {
            Week week = new Week();
        }
        System.out.printf("countDay: %d | countWeekPosition: %d | countMonth: %d | countYear: %d | counter: %d\n", countDay, countWeekPosition, countMonth, countYear, counter);

        //return yearArray;
    }

    public static void main(String[] args)
    {
        /*
        Day someday = new Day(1, 30);
        Day someday1 = new Day(2, 31);
        Day someday2 = new Day(3, 32);
        Day someday3 = new Day(4, 33);
        Day someday4 = new Day(5, 34);

        Week someweek = new Week();
        someweek.setDay(someday, 3);
        someweek.setDay(someday1, 4);
        someweek.setDay(someday2, 5);
        someweek.setDay(someday3, 6);
        someweek.setDay(someday4, 7);

        Month somemonth = new Month();
        somemonth.addWeek(someweek);

        for (Week w : somemonth.getMonth())
            if (w != null)
                for (Day d : someweek.getWeek())
                    if (d != null)
                        System.out.println(d.getDay());
                    else
                        System.out.println("null");
        */
        Cal col = new Cal();
        col.getRangeOfMonths(3, 2022, 9, 2022);

    }
}
