public class Month {
    // external
    private Week[] month = new Week[] {null, null, null, null, null, null};
    private int monthNumber;

    // internal
    private int weeks = 0;

    public Month(int monthNumber) 
    {
        this.monthNumber= monthNumber;
    }

    public Month(Week[] weeks, int monthNumber) {
        for (int i = 0; i < 6; i++)
            month[i] = weeks[i];
        this.monthNumber = monthNumber;
    }

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

    public int getMonthNumber()
    {
        return monthNumber;
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


    public void clear()
    {
        month = new Week[] {null, null, null, null, null, null};
    }

    //public Week[] getCopyMonth() {
    //    return Arrays.copyOf(month);
    //}
}
