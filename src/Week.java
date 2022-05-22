public class Week {
    private Day[] week = new Day[] {null, null, null, null, null, null, null};

    public Week() { }

    public Week(Day[] days) {
        for (int i = 0; i < 7; i++)
            week[i] = days[i];
    }

    public void setDay(Day day, int position)
    {
        try {
            //System.out.printf(">>Add to Week: %d at %d\n", day.getDay(), position-1);
            week[position-1] = day;
            //System.out.printf("<< Print Array: %d\n", week[position-1].getDay());
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Input the index starting from one as in the first Day of the Week\nError:\n" + e);
        }
    }

    public Day[] getWeek()
    {
        return week;
    }

    public boolean isEmpty()
    {
        int counter = 0;

        for (Day d : week)
            if (d == null)
                counter++;

        if (counter == 7)
            return true;

        return false;
    }

    public void clear()
    {
        week = new Day[] {null, null, null, null, null, null, null};
    }

    //public Day[] getCopyWeek() {
    //    return Arrays.copyOf(week);
    //}

}
