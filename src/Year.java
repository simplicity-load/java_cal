public class Year {
    // external
    private Month[] year = new Month[] {null, null, null, null, null, null, null, null, null, null, null, null};
    private int yearNumber;

    // internal

    public Year(int yearNumber) 
    {
        this.yearNumber = yearNumber;
    }

    public void setMonth(Month month, int position)
    {
        try {
            year[position-1] = month;
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Input the index starting from one as in the first Month of the Year\nError:\n" + e);
        }
    }

    public Month[] getYear()
    {
        return year;
    }

    public int getYearNumber()
    {
        return yearNumber;
    }
}
