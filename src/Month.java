/*
    Cal(1) clone written in Java
    Copyright (C) 2022  simplicity-load
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.
    
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.
    
    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

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
