/** The Week Class helps us keep track of days and their position in the week
  */
public class Week {
    // A null initalized array which holds 7 Day objects (maximum number of days inside a week)
    private Day[] week = new Day[] {null, null, null, null, null, null, null};

    /** Initialize an empty week
      */
    public Week() { }

    /** Setter for this object's Day array property, 
        this method is usually used to create a deep copy of another Week object
      * @param days The Day array which will be used as this object's Day array
      */
    public Week(Day[] days) 
    {
        for (int i = 0; i < 7; i++)
            week[i] = days[i];
    }

    /** Insert a day in a specific position of the week
      * @param day Day object to insert in the array
      * @param position Position to insert the Day object
      */
    public void setDay(Day day, int position)
    {
        try {
            week[position-1] = day;
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Input the index starting from one as in the first Day of the Week\nError:\n" + e);
        }
    }

    /** Getter for the Day array of this object
      * @return A reference to this object's Day array
      */
    public Day[] getWeek()
    {
        return week;
    }

    /** Check if the week is totally empty
      * @return Result of the method
      */
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

    /** Null out this object's Day array
      */
    public void clear()
    {
        week = new Day[] {null, null, null, null, null, null, null};
    }

}
