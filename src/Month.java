public class Month {
    private Week[] month = new Week[] {null, null, null, null, null, null};
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
}
