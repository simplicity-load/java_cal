import java.time.LocalDate;

public class InputView extends View {

    public int beginMonth = month;
    public int beginYear = year;

    public int endMonth = month;
    public int endYear = year;

    private String[] args;

    private String helpString = "Usage:\n"
                              + "\n"
                              + "aa\n";

    public InputView(String[] args)
    {
        this.args = args;
    }

    //Input View
    public boolean parseArgs()
    { 
        if (args.length == 0)
            return true;

        int argPush = 0;
        if (args[0].charAt(0) == '-')
            if (args[0].equals("-n"))
                argPush = 2;
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
