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

/** The Day Class is the most basic unit of the Calendar
  */
public class Day {
    /** This property holds the `int` which represents the day of the month
      */
    private int day;

    /** (Constructor and) Setter for this object's day property
      * @param day Value to set the day property
      */
    public Day(int day)
    {
        this.day = day;
    }

    /** The day property getter
      * @return Value of the day property
      */
    public int getDay()
    {
        return day;
    }

}
