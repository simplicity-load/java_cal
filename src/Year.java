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

/** The Year Class helps us keep track of a year's months structured as Month objects
  */
public class Year {
    // Class properties
    // A null initalized array which holds 12 Month objects (maximum number of months inside a year)
    private Month[] year = new Month[] {null, null, null, null, null, null, null, null, null, null, null, null};
    // The year number
    private int yearNumber;

    /** Initialize an empty Year
      * @param yearNumber Sets the yearNumber property
      */
    public Year(int yearNumber) 
    {
        this.yearNumber = yearNumber;
    }

    /** Setter for this object's Month array property, 
        this method is usually used to create a deep copy of another Year object
      * @param months The Month array which will be used as this object's Month array
      * @param yearNumber Sets the yearNumber property
      */
    public Year(Month[] months, int yearNumber) 
    {
        for (int i = 0; i < 12; i++)
            year[i] = months[i];
        this.yearNumber = yearNumber;
    }

    /** Insert a month in a specific position of the year
      * @param month Month object to insert in the array
      * @param position Position to insert the Month object
      */
    public void setMonth(Month month, int position)
    {
        try {
            year[position-1] = month;
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Input the index starting from one as in the first Month of the Year\nError:\n" + e);
        }
    }

    /** Getter for the Month array of this object
      * @return A reference to this object's Month array
      */
    public Month[] getYear()
    {
        return year;
    }

    /** Getter for the number of the year
      * @return This Year's number
      */
    public int getYearNumber()
    {
        return yearNumber;
    }

    /** Null out this object's Month array
      */
    public void clear()
    {
        year = new Month[] {null, null, null, null, null, null, null, null, null, null, null, null};
    }

}
