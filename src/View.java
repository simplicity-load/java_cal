import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Map;
import java.util.HashMap;

public class View {

    private LocalDate todayDate = LocalDate.now();
    private int day = todayDate.getDayOfMonth();
    private int month = todayDate.getMonthValue();
    private int year = todayDate.getYear();

    private String[] args = null;

    HashMap<Integer, String[]> months = new HashMap<Integer, String[]>() {
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

    public View(String[] args) {
        this.args = args;
    }

    public boolean parseArgs(String[] args)
    {
        if (args.length == 0)
            return false;

        if (args[0].charAt(0) == '-') {
            switch(args[0]) {
                case "-1" : printRange(month, year, month, year);

                case "-3" : todayDate.minusMonths(1);
                            int lastMonth = todayDate.getMonthValue();
                            int lastYear = todayDate.getYear();
                            todayDate.plusMonths(1);
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

    private void printRange(int beginMonth, int beginYear, int endMonth, int endYear) 
    {
        //
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
