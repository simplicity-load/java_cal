import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class OutputView extends View {

    public OutputView() { }


    public void printRange(int beginMonth, int beginYear, int endMonth, int endYear) 
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
            }
        }
    }
}
