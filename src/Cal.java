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
        a
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
