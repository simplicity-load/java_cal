/** The Month Class helps us keep track of a month and its days structured as Week objects
  */
public class Month {
    // Class properties
    // A null initalized array which holds 6 Week objects (maximum number of weeks inside a month)
    private Week[] month = new Week[] {null, null, null, null, null, null};
    // The number of the month (e.g. January is the first, so this would have the value of 1)
    private int monthNumber;

    // Local variable
    // A counter used to incrementally put Week objects in the Week array
    private int weeks = 0;

    /** Initialize an empty Month
      * @param monthNumber Sets the monthNumber property
      */
    public Month(int monthNumber) 
    {
        this.monthNumber = monthNumber;
    }

    /** Setter for this object's Week array property, 
        this method is usually used to create a deep copy of another Month object
      * @param weeks The Week array which will be used as this object's Week array
      * @param monthNumber Sets the monthNumber property
      */
    public Month(Week[] weeks, int monthNumber) {
        for (int i = 0; i < 6; i++)
            month[i] = weeks[i];
        this.monthNumber = monthNumber;
    }

    /** Append a week to the Week array
      * @param week Day object to insert in the array
      */
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

    /** Getter for the Week array of this object
      * @return A reference to this object's Week array
      */
    public Week[] getMonth()
    {
        return month;
    }

    /** Getter for the number of the month
      * @return This Month's number
      */
    public int getMonthNumber()
    {
        return monthNumber;
    }

    /** Check if the month is totally empty
      * @return Result of the method
      */
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


    /** Null out this object's Week array
      */
    public void clear()
    {
        month = new Week[] {null, null, null, null, null, null};
    }

}
