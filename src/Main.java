class Main {
    public static void main(String[] args) {
        InputView iv = new InputView(args);
        OutputView ov = new OutputView();
        if (iv.parseArgs())
            ov.printRange(iv.beginMonth, iv.beginYear, iv.endMonth, iv.endYear);
    }
}
