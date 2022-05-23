import java.time.LocalDate;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

/** The View Class is an abstract class which provides some basic data needed to construct input or output views
  */
abstract class View {

    // Today's date
    public LocalDate todayDate = LocalDate.now();
    public int day = todayDate.getDayOfMonth();
    public int month = todayDate.getMonthValue();
    public int year = todayDate.getYear();

    // Month names and their acronyms(in multiple languages)
    public final static int type_of_month_lang = 2;
    public Map<Integer, String[]> months = new HashMap<Integer, String[]>() {
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


    // Week labels (in multiple languages)
    public final static int type_of_week_lang = 0;
    public Map<Integer, String[]> weeks = new LinkedHashMap<Integer, String[]>() {
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
}
