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

/** The Calendar calculates a given range of dates and returns a Year array 
  */
public class Cal {

    /** Constructor
      */
    public Cal() { }

    // How the first day of the first year starts out
    private static final int initial_day = 1;
    private static final int initial_week_position = 6;
    private static final int initial_month = 1;
    private static final int initial_year = 1;

    /** Given a range of dates inclusively return a proportionally sized Year array
      * @param beginMonth The month of the date from which to start
      * @param beginYear The year of the date from which to start
      * @param endMonth The month of the date to end
      * @param endYear The year of the date to end
      * @return A proportionally sized Year array
      */
    public Year[] getRangeOfMonths(int beginMonth, int beginYear, int endMonth, int endYear)
    {
        // Check if the range of dates is given correctly and exit otherwise
        if (beginMonth < 0 || endMonth < 0 ||
            beginMonth > 12 || endMonth > 12 ||
            beginYear < 0 || endYear < 0 ||
            beginYear > endYear ||
            (beginMonth > endMonth && beginYear == endYear)) {

            System.out.println("You inputted wrong actual parameters for the Calendar");
            System.exit(1);
        }

        // Size of the Year array (plus 1 since it inclusively calculates years)
        // and a Year array created with that size
        int number_of_years = endYear - beginYear + 1;
        Year[] yearArray = new Year[number_of_years];


        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int countDay = initial_day;
        int countWeekPosition = initial_week_position;
        int countMonth = initial_month;
        int countYear = initial_year;

        while (countYear <= endYear) {

            // Extensively checking for leap years
            if (countYear <= 1752 && countYear % 4 == 0)
                daysInMonth[1] = 29;
            else if((countYear % 4 == 0 && countYear % 100 != 0) || countYear % 400 == 0)
                daysInMonth[1] = 29;
            else
                daysInMonth[1] = 28;

            // Create a new Year object
            Year year = new Year(countYear);
            // Correctly add fitting months to the above Year object
            while (countMonth <= 12) {
                Month month = new Month(countMonth);
                Week week = new Week();
                // Correctly add days to the above Week object 
                // and package those Week objects into the Month object above
                while (countDay <= daysInMonth[countMonth-1]) {

                    if (countWeekPosition == 7) {
                        countWeekPosition = 1;
                        if (countYear >= beginYear && countMonth >= beginMonth || countYear > beginYear) {
                            // Package a week
                            week.setDay(new Day(countDay), 7);
                            month.addWeek(new Week(week.getWeek()));
                            week.clear();
                        }
                    }
                    else if (!(countYear == 1752 && countMonth == 9 && countDay > 2 && countDay < 14)) {
                        if (countYear >= beginYear && countMonth >= beginMonth || countYear > beginYear)
                            week.setDay(new Day(countDay), countWeekPosition);
                        countWeekPosition++;
                    }
                    countDay++;
                }
                if (countYear >= beginYear && countMonth >= beginMonth || countYear > beginYear) {
                    // Package a Month object into the Year object
                    month.addWeek(new Week(week.getWeek()));
                    year.setMonth(new Month(month.getMonth(), countMonth), countMonth);
                    month.clear();
                }
                if (countMonth >= endMonth && countYear == endYear)
                    break;
                countDay = 1;
                countMonth++;
            }
            // If our counters have arrived to the range of dates which it's going to package:
            // package the Year in the yearArray
            if (countYear >= beginYear) {
                yearArray[countYear-beginYear] = new Year(year.getYear(), countYear);
                year.clear();
            }

            if (countYear == endYear)
                break;

            countMonth = 1;
            countYear++;
        }

        // Return the Year array
        return yearArray;
    }

}
