import java.time.LocalDate;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class View {

    private LocalDate todayDate = LocalDate.now();
    private int day = todayDate.getDayOfMonth();
    private int month = todayDate.getMonthValue();
    private int year = todayDate.getYear();

    private final static int type_of_month_lang = 2;
    Map<Integer, String[]> months = new HashMap<Integer, String[]>() {
        {
            put(1,	new String[] {"Jan",	"Jan",	"Janar",	"January"});
            put(2,	new String[] {"Shk",	"Feb",	"Shkurt",	"February"});
            put(3,	new String[] {"Mar",	"Mar",	"Mars",	    "March"});
            put(4,	new String[] {"Pri",	"Apr",	"Prill",	"April"});
            put(5,	new String[] {"Maj",	"May",	"Maj",	    "May"});
            put(6,	new String[] {"Qer",	"Jun",	"Qershor",	"June"});
            put(7,	new String[] {"Kor",	"Jul",	"Korrik",	"July"});
            put(8,	new String[] {"Gus",	"Aug",	"Gusht",	"August"});
            put(9,	new String[] {"Sht",	"Sep",	"Shtator",	"September"});
            put(10,	new String[] {"Tet",	"Oct",	"Tetor",	"October"});
            put(11,	new String[] {"Nen",	"Nov",	"Nentor",	"November"});
            put(12,	new String[] {"Dhj",	"Dec",	"Dhjetor",	"December"});
        }
    };


    private final static int type_of_week_lang = 0;
    Map<Integer, String[]> weeks = new LinkedHashMap<Integer, String[]>() {
        {
            put(1,	new String[] {"Ha", "Mo"});
            put(2,	new String[] {"Ma", "Tu"});
            put(3,	new String[] {"Me", "We"});
            put(4,	new String[] {"En", "Th"});
            put(5,	new String[] {"Pr", "Fr"});
            put(6,	new String[] {"Sh", "Sa"});
            put(7,	new String[] {"Di", "Su"});
        }
    };

    public View(String[] args) {
        parseArgs(args);
        // TODO print help
    }

    //Input View
    private boolean parseArgs(String[] args)
    {
        if (args.length == 0) {
            printRange(month, year, month, year);
            return true;
        }

        if (args[0].charAt(0) == '-') {
            switch(args[0]) {
                case "-1" : printRange(month, year, month, year);
                            break;

                case "-3" : LocalDate minusOne = todayDate.minusMonths(1);
                            int lastMonth = minusOne.getMonthValue();
                            int lastYear =  minusOne.getYear();

                            LocalDate plusOne = todayDate.plusMonths(1);
                            int nextMonth = plusOne.getMonthValue();
                            int nextYear =  plusOne.getYear();
                            printRange(lastMonth, lastYear, nextMonth, nextYear);
                            break;

                case "-n" : LocalDate plusN = todayDate.plusMonths(Integer.valueOf(args[1])-1);
                            int endMonth = plusN.getMonthValue();
                            int endYear =  plusN.getYear();
                            printRange(month, year, endMonth, endYear);
                            break;

                default :   return false;
            }
            return true;
        }

        if (args.length == 1) {
            printRange(1, Integer.valueOf(args[0]), 12, Integer.valueOf(args[0]));
            return true;
        }

        if (args.length == 2) {
            int _month = stringToMonth(args[0]);
            int _year = Integer.valueOf(args[1]);
            printRange(_month, _year, _month, _year);
            return true;
        }

        if (args.length == 4) {
            int beginMonth = stringToMonth(args[0]);
            int beginYear = Integer.valueOf(args[1]);

            int endMonth = stringToMonth(args[2]);
            int endYear = Integer.valueOf(args[3]);

            printRange(beginMonth, beginYear, endMonth, endYear);
            return true;
        }

        return false;
    }

    //Output View
    private void printRange(int beginMonth, int beginYear, int endMonth, int endYear) 
    {
        Cal calendar = new Cal();
        Year[] yArr = calendar.getRangeOfMonths(beginMonth, beginYear, endMonth, endYear);

        //one month
        if (beginMonth == endMonth && beginYear == endYear) {
            for(Month m : yArr[0].getYear()) {
                if(m != null) {
                    String monthHeader = months.get(m.getMonthNumber())[type_of_month_lang] + " " + yArr[0].getYearNumber();
                    // mHL - monthHeaderLength
                    int mHL = monthHeader.length();
                    String formattedMonthHeader = String.format("%" + (20/2 - mHL/2) + "s%s", "", monthHeader);
                    System.out.println(formattedMonthHeader);
                    weeks.forEach((k,v) -> {
                        System.out.printf(v[type_of_week_lang] + " ");
                    });
                    System.out.printf("\n");
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
            return;
        }
        for (Year y : yArr) {
            int mCounter = 0;
            int month_cols = 3;

            String formattedYearHeader = String.format("%" + ((21*month_cols)/2 - (y.getYearNumber()+"").length()/2) + "s%d\n", "", y.getYearNumber());
            System.out.println(formattedYearHeader);

            Month[] mArr = new Month[] {null, null, null};
            for (Month m : y.getYear()) {
                if(m != null) {
                    mCounter++;
                    mArr[mCounter-1] = m;
                    if (mCounter == month_cols || m.getMonthNumber() == 12 ||
                        m.getMonthNumber() == endMonth && y.getYearNumber() == endYear) {
                        for (int i = 0; i < mCounter; i++) {
                            String mName = months.get(mArr[i].getMonthNumber())[type_of_month_lang];
                            //before name space
                            int bSpace = 20/2 - mName.length()/2;
                            //after name space
                            int aSpace = 20 - (bSpace + mName.length());
                            System.out.printf(String.format("%" + bSpace + "s%s%" + aSpace + "s  ", "", mName, ""));
                        }
                        System.out.printf("\n");
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

                        for (int i = 0; i < 6; i++) {
                            for (int j = 0; j < month_cols; j++) {
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
                    //for (Week w : m.getMonth()) {
                    //    System.out.println("W");
                    //    if(w != null)
                    //        for (Day d : w.getWeek())
                    //            if(d != null)
                    //                System.out.printf(" %2d ", d.getDay());
                    //            else
                    //                System.out.printf("    ");
                    //}
            }
        }
    }

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
