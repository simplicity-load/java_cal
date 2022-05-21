public class Cal {

    public Cal() { }

    //TODO get only the specified month
    public Year[] getMonth(int month, int year)
    {
        return getRangeOfMonths(month, year, month, year);
    }

    //TODO get a range of months including BEGIN and END limits
    public Year[] getRangeOfMonths(int beginMonth, int beginYear, int endMonth, int endYear)
    {
        if (beginMonth < 0 ||
            beginYear < 0 ||
            endMonth < 0 ||
            endYear < 0 ||
            beginMonth > endYear ||
            (beginMonth < endMonth && beginYear == endYear)) {

            System.out.println("You inputted wrong actual parameters for the Calendar");
            System.exit(1);
        }

        // How the first day of the first year starts out
        int intialDay = 1;
        int initialYear = 1;
        int initialWeekPosition = 5;
        return new Year[] {new Year(2022)};
    }

    public static void main(String[] args)
    {
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

    }
}
