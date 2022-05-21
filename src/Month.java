public class Month {
    // external
    private Week[] month = new Week[] {null, null, null, null, null, null};
    private String monthName;

    // internal
    private int weeks = 0;

    public Month() { }

    public void addWeek(Week week)
    {
        try {
            month[weeks] = week;
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Too many weeks in one Month\nError:\n" + e);
        }
        weeks++;
    }

    public Week[] getMonth()
    {
        return month;
    }

    public void setMonthName(String monthName)
    {
        this.monthName = monthName;
    }


    public String getMonthName()
    {
        return monthName;
    }


    public boolean isEmpty()
    {
        int counter = 0;

        for (Week w : month)
            if (w == null)
                counter++;

        if (counter == 7)
            return true;

        return false;
    }
}
