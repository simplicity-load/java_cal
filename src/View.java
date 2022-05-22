import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
            put(1,	new String[] {"jan",	"jan",	"janar",	"january"});
            put(2,	new String[] {"shk",	"feb",	"shkurt",	"february"});
            put(3,	new String[] {"mar",	"mar",	"mars",	    "march"});
            put(4,	new String[] {"pri",	"apr",	"prill",	"april"});
            put(5,	new String[] {"maj",	"may",	"maj",	    "may"});
            put(6,	new String[] {"qer",	"jun",	"qershor",	"june"});
            put(7,	new String[] {"kor",	"jul",	"korrik",	"july"});
            put(8,	new String[] {"gus",	"aug",	"gusht",	"august"});
            put(9,	new String[] {"sht",	"sep",	"shtator",	"september"});
            put(10,	new String[] {"tet",	"oct",	"tetor",	"october"});
            put(11,	new String[] {"nen",	"nov",	"nentor",	"november"});
            put(12,	new String[] {"dhj",	"dec",	"dhjetor",	"december"});
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
    }

    //Input View
    private boolean parseArgs(String[] args)
    {
        if (args.length == 0)
            return false;

        if (args[0].charAt(0) == '-') {
            switch(args[0]) {
                case "-1" : printRange(month, year, month, year);

                case "-3" : todayDate.minusMonths(1);
                            int lastMonth = todayDate.getMonthValue();
                            int lastYear = todayDate.getYear();
                            todayDate.plusMonths(2);
                            printRange(lastMonth, lastYear, todayDate.getMonthValue(), todayDate.getYear());

                case "-n" : todayDate.plusMonths(Integer.valueOf(args[1]));
                            printRange(month, year, todayDate.getMonthValue(), todayDate.getYear());

                default :   return true;
            }
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
                                    System.out.printf("%2d ", d.getDay());
                                else
                                    System.out.printf("   ");
                        System.out.printf("\n");
                    }
                }
            }
            return;
        }
        for (Year y : yArr) {
            String formattedYearHeader = String.format("%" + (64/2 - (y.getYearNumber()+"").length()/2) + "s%d\n", "", y.getYearNumber);
            System.out.println(formattedYearHeader);
            for (Month m : y.getYear()) {
                System.out.println("M");
                if(m != null)
                    for (Week w : m.getMonth()) {
                        System.out.println("W");
                        if(w != null)
                            for (Day d : w.getWeek())
                                if(d != null)
                                    System.out.printf(" %2d ", d.getDay());
                                else
                                    System.out.printf("    ");
                    }
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
                    if (query.equals(s))
                        return k;
        }
        return -1;
    }
}
