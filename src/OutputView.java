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

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

/** The OutputView Class serves as a means to prettily display a Year array
  */
public class OutputView extends View {

    /** Constructor
      */
    public OutputView() { }

    /** Given a range of dates inclusively display the corresponding months of the range of dates
      * @param beginMonth The month of the date from which to start
      * @param beginYear The year of the date from which to start
      * @param endMonth The month of the date to end
      * @param endYear The year of the date to end
      */
    public void printRange(int beginMonth, int beginYear, int endMonth, int endYear) 
    {
        // Use the Cal Class to get a Year array for the method parameters
        Cal calendar = new Cal();
        Year[] yArr = calendar.getRangeOfMonths(beginMonth, beginYear, endMonth, endYear);

        // If the range specifies only one month then only print one Month
        if (beginMonth == endMonth && beginYear == endYear) {
            for(Month m : yArr[0].getYear()) {
                if(m != null) {
                    // Month header formatting
                    String monthHeader = months.get(m.getMonthNumber())[type_of_month_lang] + " " + yArr[0].getYearNumber();
                    // mHL - monthHeaderLength
                    int mHL = monthHeader.length();
                    // Display the month label
                    String formattedMonthHeader = String.format("%" + (20/2 - mHL/2) + "s%s", "", monthHeader);
                    System.out.println(formattedMonthHeader);
                    // Display the week day labels
                    weeks.forEach((k,v) -> {
                        System.out.printf(v[type_of_week_lang] + " ");
                    });
                    System.out.printf("\n");
                    // Display the days
                    for (Week w : m.getMonth()) {
                        if(w != null)
                            for (Day d : w.getWeek())
                                if(d != null)
                                    if (day == d.getDay() && month == m.getMonthNumber() && year == yArr[0].getYearNumber())
                                        System.out.printf("%s%s%s%2d%s ", "\u001B[0m", "\u001B[107m", "\u001B[30m", d.getDay(), "\u001B[0m");
                                    else
                                        System.out.printf("%2d ", d.getDay());
                                else
                                    System.out.printf("   ");
                        else
                            System.out.printf("                      ");
                        System.out.printf("\n");
                    }
                }
            }
            // Return Null after displaying the Month
            return;
        }


        // Display the range of dates
        for (Year y : yArr) {

            // Local variable (to count up to three months)
            int mCounter = 0;

            // Formatted year label
            String formattedYearHeader = String.format("%" + ((21*3)/2 - (y.getYearNumber()+"").length()/2) + "s%d\n", "", y.getYearNumber());
            System.out.println(formattedYearHeader);

            Month[] mArr = new Month[] {null, null, null};

            // Add three months to the Month array mArr
            for (Month m : y.getYear()) {
                if(m != null) {
                    mCounter++;
                    mArr[mCounter-1] = m;
                    // If we have 3 months (or this month is the last of the year) then display them
                    if (mCounter == 3 || m.getMonthNumber() == 12 ||
                        m.getMonthNumber() == endMonth && y.getYearNumber() == endYear) {
                        for (int i = 0; i < mCounter; i++) {
                            String mName = months.get(mArr[i].getMonthNumber())[type_of_month_lang];
                            //before name space
                            int bSpace = 20/2 - mName.length()/2;
                            //after name space
                            int aSpace = 20 - (bSpace + mName.length());
                            // Month format
                            System.out.printf(String.format("%" + bSpace + "s%s%" + aSpace + "s  ", "", mName, ""));
                        }
                        System.out.printf("\n");
                        // Display Month headers
                        for (int i = 0; i < mCounter; i++) {
                            weeks.forEach((k,v) -> {
                                if (k == 7)
                                    System.out.printf(v[type_of_week_lang]);
                                else
                                    System.out.printf(v[type_of_week_lang] + " ");
                            });
                            System.out.printf("  ");
                        }
                        System.out.printf("\n");

                        // Display the days of the Month objects in the mArr
                        for (int i = 0; i < 6; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (mArr[j] != null) {
                                    Week _week = (mArr[j].getMonth())[i];
                                    if (_week != null) {
                                        Day[] days = _week.getWeek();
                                        for (int k = 0; k < 7; k++) {
                                            if(days[k] != null)
                                                if (day == days[k].getDay() && month == mArr[j].getMonthNumber() && year == y.getYearNumber())
                                                    System.out.printf("%s%s%s%2d%s ", "\u001B[0m", "\u001B[107m", "\u001B[30m", days[k].getDay(), "\u001B[0m");
                                                else if (k == 6)
                                                    System.out.printf("%2d  ", days[k].getDay());
                                                else
                                                    System.out.printf("%2d ", days[k].getDay());
                                            else if (k == 6)
                                                System.out.printf("    ");
                                            else
                                                System.out.printf("   ");
                                        }
                                    } else
                                        System.out.printf("                      ");
                                }
                            }
                            System.out.printf("\n");
                        }
                        System.out.printf("\n");

                        mCounter = 0;
                        mArr = new Month[] {null, null, null};
                    }
                }
            }
        }
    }
}
