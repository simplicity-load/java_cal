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

import java.time.LocalDate;

/** The InputView Class serves as a means to gather input from the user
  */
public class InputView extends View {

    // Public properties which define the date range
    public int beginMonth = month;
    public int beginYear = year;

    public int endMonth = month;
    public int endYear = year;

    // String array of input
    private String[] args;

    // The quick help string
    private String helpString = "cal(1) clone (in Java)\n\n"
                              + "Usage:\n"
                              + "java Main [options] [[month] year] [month year]\n"
                              + "\n"
                              + "Display a calendar, or some part of it.\n"
                              + "Without any arguments, display the current month.\n"
                              + "\n"
                              + "Options:\n"
                              + "-1\t\t\tshow only a single month (default)\n"
                              + "-3\t\t\tshow three months spanning the date\n"
                              + "-n <num>  \tshow num months starting with date's month\n"
                              + "-y\t\t\tshow the whole year\n";

    /** Initialize this class as an object to parse input
      * @param args Input to be parsed as a String array
      */
    public InputView(String[] args)
    {
        this.args = args;
    }

    /** Parse the input and set the properties of this object to the parsed values
      * @return The success of the method
      */
    public boolean parseArgs()
    { 
        if (args.length == 0)
            return true;

        int argPush = 0;
        if (args[0].charAt(0) == '-')
            if (args[0].equals("-n"))
                argPush = 2;
            else if (args[0].equals("-y")) {
                beginMonth = 0;
                endMonth = 12;
                return true;
            }
            else
                argPush = 1;

        if (args.length == 1+argPush) {
            beginMonth = 0;
            beginYear = Integer.valueOf(args[0+argPush]);

            endMonth = 12;
            endYear = Integer.valueOf(args[0+argPush]);
            return true;
        }

        if (args.length == 2+argPush) {
            beginMonth = endMonth = stringToMonth(args[0+argPush]);
            beginYear = endYear = Integer.valueOf(args[1+argPush]);
        }

        if (args.length == 4+argPush) {
            beginMonth = stringToMonth(args[0+argPush]);
            beginYear = Integer.valueOf(args[1+argPush]);

            endMonth = stringToMonth(args[2+argPush]);
            endYear = Integer.valueOf(args[3+argPush]);
            return true;
        }

        LocalDate date = LocalDate.of(beginYear, beginMonth, 1);
        if (argPush == 0)
            return true;
        else {
            switch(args[0]) {
                case "-1" : break;

                case "-3" : LocalDate minusOne = date.minusMonths(1);
                            beginMonth = minusOne.getMonthValue();
                            beginYear  = minusOne.getYear();

                            LocalDate plusOne = date.plusMonths(1);
                            endMonth = plusOne.getMonthValue();
                            endYear  = plusOne.getYear();
                            break;

                case "-n" : LocalDate plusN = date.plusMonths(Integer.valueOf(args[1])-1);
                            endMonth = plusN.getMonthValue();
                            endYear  = plusN.getYear();
                            break;
                case "-h" :
                default   : System.out.println(helpString);
                            return false;
            }
            return true;
        }
    }

    /** Given a String, get the month number from it
      * @param query String to be turned into appropriate data
      * @return The month number
      */
    private int stringToMonth(String query)
    {
        try {
            return Integer.valueOf(query);
        } catch (NumberFormatException e) {
            for (int k : months.keySet())
                for (String s : months.get(k))
                    if ((query.toLowerCase()).equals(s.toLowerCase()))
                        return k;
        }
        return -1;
    }

}
