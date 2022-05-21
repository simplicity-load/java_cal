public class Month {
    // external
    private Week[] month = new Week[] {null, null, null, null, null, null};
    private String monthName;

    // internal
    private int weeks = 0;

    public Month() { }

    public void addWeek(Week week)
    {
        month[weeks] = week;
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
}
